package com.java1234.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;

import com.java1234.dao.CategoryDao;
import com.java1234.dao.ProductDao;
import com.java1234.model.Category;
import com.java1234.model.Product;
import com.java1234.util.DbUtil;

public class ViewEditDeleteProductPage extends JFrame {

    private JTable productTable;
    private DefaultTableModel tableModel;
    private JTextField idTxt;
    private JTextField nameTxt;
    private JComboBox<String> categoryCombo;
    private JTextField priceTxt;
    
    private DbUtil dbUtil = new DbUtil();
    private CategoryDao categoryDao = new CategoryDao();
    private ProductDao productDao = new ProductDao();

    public ViewEditDeleteProductPage() {
        setTitle("View, Edit & Delete Product");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel titleLbl = new JLabel("View, Edit & Delete Product");
        titleLbl.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLbl.setBounds(20, 20, 400, 30);
        titleLbl.setIcon(new ImageIcon("src/images/view edit delete product.png"));
        getContentPane().add(titleLbl);

        JButton btnClose = new JButton("");
        btnClose.setIcon(new ImageIcon("src/images/close.png"));
        btnClose.setBounds(730, 20, 30, 30);
        btnClose.addActionListener(e -> setVisible(false));
        getContentPane().add(btnClose);

        // Edit Form
        int startX = 20;
        int startY = 100;
        int gapY = 40;

        JLabel lblId = new JLabel("ID");
        lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblId.setBounds(startX, startY, 80, 20);
        getContentPane().add(lblId);

        idTxt = new JTextField();
        idTxt.setBounds(startX + 80, startY, 200, 30);
        idTxt.setEditable(false);
        getContentPane().add(idTxt);

        startY += gapY;
        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblName.setBounds(startX, startY, 80, 20);
        getContentPane().add(lblName);

        nameTxt = new JTextField();
        nameTxt.setBounds(startX + 80, startY, 200, 30);
        getContentPane().add(nameTxt);

        startY += gapY;
        JLabel lblCategory = new JLabel("Category");
        lblCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCategory.setBounds(startX, startY, 80, 20);
        getContentPane().add(lblCategory);

        categoryCombo = new JComboBox<>();
        categoryCombo.setBounds(startX + 80, startY, 200, 30);
        getContentPane().add(categoryCombo);

        startY += gapY;
        JLabel lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPrice.setBounds(startX, startY, 80, 20);
        getContentPane().add(lblPrice);

        priceTxt = new JTextField();
        priceTxt.setBounds(startX + 80, startY, 200, 30);
        getContentPane().add(priceTxt);

        // Buttons
        startY += gapY + 10;
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setIcon(new ImageIcon("src/images/save.png"));
        btnUpdate.setBounds(startX, startY, 90, 30);
        btnUpdate.addActionListener(e -> updateAction());
        getContentPane().add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setIcon(new ImageIcon("src/images/delete.png"));
        btnDelete.setBounds(startX + 100, startY, 90, 30);
        btnDelete.addActionListener(e -> deleteAction());
        getContentPane().add(btnDelete);

        JButton btnClear = new JButton("Clear");
        btnClear.setIcon(new ImageIcon("src/images/clear.png"));
        btnClear.setBounds(startX + 200, startY, 80, 30);
        btnClear.addActionListener(e -> clearAction());
        getContentPane().add(btnClear);

        // Table
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Category", "Price"}, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(330, 100, 430, 300);
        getContentPane().add(scrollPane);

        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = productTable.getSelectedRow();
                if (row != -1) {
                    idTxt.setText(tableModel.getValueAt(row, 0).toString());
                    nameTxt.setText(tableModel.getValueAt(row, 1).toString());
                    categoryCombo.setSelectedItem(tableModel.getValueAt(row, 2).toString());
                    priceTxt.setText(tableModel.getValueAt(row, 3).toString());
                }
            }
        });

        // Background Image
        JLabel background = new JLabel(new ImageIcon("src/images/small-page-background.png"));
        background.setBounds(0, 0, 800, 500);
        getContentPane().add(background);

        loadCategory();
        loadTableData();
    }

    private void loadCategory() {
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ArrayList<Category> list = categoryDao.getAllRecords(con);
            for (Category c : list) {
                categoryCombo.addItem(c.getName());
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { dbUtil.closeCon(con); } catch(Exception e){}
        }
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ArrayList<Product> list = productDao.getAllRecords(con);
            for (Product p : list) {
                tableModel.addRow(new Object[]{p.getId(), p.getName(), p.getCategory(), p.getPrice()});
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { dbUtil.closeCon(con); } catch(Exception e){}
        }
    }

    private void updateAction() {
        if(idTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Select a product to update!");
            return;
        }
        Product product = new Product();
        product.setId(Integer.parseInt(idTxt.getText()));
        product.setName(nameTxt.getText());
        product.setCategory((String)categoryCombo.getSelectedItem());
        product.setPrice(priceTxt.getText());

        Connection con = null;
        try {
            con = dbUtil.getCon();
            productDao.update(con, product);
            JOptionPane.showMessageDialog(null, "Product Updated Successfully!");
            clearAction();
            loadTableData();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try { dbUtil.closeCon(con); } catch(Exception e){}
        }
    }

    private void deleteAction() {
        if(idTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Select a product to delete!");
            return;
        }
        int a = JOptionPane.showConfirmDialog(null, "Do you really want to delete product?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            Connection con = null;
            try {
                con = dbUtil.getCon();
                productDao.delete(con, idTxt.getText());
                JOptionPane.showMessageDialog(null, "Product Deleted Successfully!");
                clearAction();
                loadTableData();
            } catch(Exception ex) {
                ex.printStackTrace();
            } finally {
                try { dbUtil.closeCon(con); } catch(Exception e){}
            }
        }
    }

    private void clearAction() {
        idTxt.setText("");
        nameTxt.setText("");
        priceTxt.setText("");
    }
}
