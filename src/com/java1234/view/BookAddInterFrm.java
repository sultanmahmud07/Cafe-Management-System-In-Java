package com.java1234.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

import com.java1234.dao.BookDao;
import com.java1234.dao.BookTypeDao;
import com.java1234.model.Book;
import com.java1234.model.BookType;
import com.java1234.util.DbUtil;
import com.java1234.util.StringUtil;

public class BookAddInterFrm extends JInternalFrame {
    private JTextField bookNameTxt;
    private JTextField authorTxt;
    private JTextField priceTxt;
    private JRadioButton manJrb;
    private JRadioButton femaleJrb;
    private JTextArea bookDescTxt;
    private JComboBox bookTypeJcb;
    private ButtonGroup buttonGroup;

    private DbUtil dbUtil = new DbUtil();
    private BookTypeDao bookTypeDao = new BookTypeDao();
    private BookDao bookDao = new BookDao();

    /**
     * Create the frame.
     */
    public BookAddInterFrm() {
        setClosable(true);
        setIconifiable(true);
        setTitle("Book Adding");
        setBounds(100, 100, 750, 500);

        JLabel lblBookName = new JLabel("Book Name:");

        bookNameTxt = new JTextField();
        bookNameTxt.setColumns(10);

        JLabel lblAuthor = new JLabel("Author:");

        authorTxt = new JTextField();
        authorTxt.setColumns(10);

        JLabel lblSex = new JLabel("Gender:");

        manJrb = new JRadioButton("Male");
        manJrb.setSelected(true);

        femaleJrb = new JRadioButton("Female");

        buttonGroup = new ButtonGroup();
        buttonGroup.add(manJrb);
        buttonGroup.add(femaleJrb);

        JLabel lblPrice = new JLabel("Price:");

        priceTxt = new JTextField();
        priceTxt.setColumns(10);

        JLabel lblBookCategory = new JLabel("Book Category:");

        bookTypeJcb = new JComboBox();

        JLabel lblDescription = new JLabel("Description:");

        bookDescTxt = new JTextArea();

        JButton btnAdd = new JButton("Add");
        btnAdd.setIcon(new ImageIcon(BookAddInterFrm.class.getResource("/images/add.png")));
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookAddActionPerformed(e);
            }
        });

        JButton btnReset = new JButton("Reset");
        btnReset.setIcon(new ImageIcon(BookAddInterFrm.class.getResource("/images/reset.png")));
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetValueActionPerformed(e);
            }
        });

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(38)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(lblDescription)
                                                .addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                                .addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 240,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(57, Short.MAX_VALUE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblBookName)
                                                        .addComponent(lblSex)
                                                        .addComponent(lblBookCategory))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE,
                                                                        100, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(26)
                                                                .addComponent(lblAuthor)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 87,
                                                                        GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addGroup(groupLayout
                                                                        .createParallelGroup(Alignment.TRAILING, false)
                                                                        .addComponent(bookTypeJcb, Alignment.LEADING, 0,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addGroup(Alignment.LEADING, groupLayout
                                                                                .createSequentialGroup()
                                                                                .addComponent(manJrb)
                                                                                .addPreferredGap(
                                                                                        ComponentPlacement.UNRELATED)
                                                                                .addComponent(femaleJrb)))
                                                                .addGap(18)
                                                                .addComponent(lblPrice)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, 68,
                                                                        GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap(47, Short.MAX_VALUE))))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(127)
                                .addComponent(btnAdd)
                                .addGap(48)
                                .addComponent(btnReset)
                                .addContainerGap(142, Short.MAX_VALUE)));
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(33)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblBookName)
                                        .addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblAuthor)
                                        .addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(26)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblSex)
                                        .addComponent(manJrb)
                                        .addComponent(femaleJrb)
                                        .addComponent(lblPrice)
                                        .addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(29)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblBookCategory)
                                        .addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(30)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblDescription)
                                        .addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 122,
                                                GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnAdd)
                                        .addComponent(btnReset))
                                .addGap(22)));
        getContentPane().setLayout(groupLayout);

        fillBookType();
    }

    /**
     * Book Add Action
     * 
     * @param e
     */
    private void bookAddActionPerformed(ActionEvent e) {
        String bookName = this.bookNameTxt.getText();
        String author = this.authorTxt.getText();
        String price = this.priceTxt.getText();
        String bookDesc = this.bookDescTxt.getText();

        if (StringUtil.isEmpty(bookName)) {
            JOptionPane.showMessageDialog(null, "Book name cannot be empty!");
            return;
        }
        if (StringUtil.isEmpty(author)) {
            JOptionPane.showMessageDialog(null, "Author cannot be empty!");
            return;
        }
        if (StringUtil.isEmpty(price)) {
            JOptionPane.showMessageDialog(null, "Price cannot be empty!");
            return;
        }

        String sex = "";
        if (manJrb.isSelected()) {
            sex = "Male";
        } else if (femaleJrb.isSelected()) {
            sex = "Female";
        }

        BookType bookType = (BookType) bookTypeJcb.getSelectedItem();
        int bookTypeId = bookType.getId();

        Book book = new Book(bookName, author, sex, Float.parseFloat(price), bookTypeId, bookDesc);

        Connection con = null;
        try {
            con = dbUtil.getCon();
            int n = bookDao.add(con, book);
            if (n == 1) {
                JOptionPane.showMessageDialog(null, "Book added successfully!");
                resetValue();
            } else {
                JOptionPane.showMessageDialog(null, "Book addition failed!");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Book addition failed!");
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Reset form values
     * 
     * @param e
     */
    private void resetValueActionPerformed(ActionEvent e) {
        this.resetValue();
    }

    private void resetValue() {
        this.bookNameTxt.setText("");
        this.authorTxt.setText("");
        this.priceTxt.setText("");
        this.manJrb.setSelected(true);
        this.bookDescTxt.setText("");
        if (this.bookTypeJcb.getItemCount() > 0) {
            this.bookTypeJcb.setSelectedIndex(0);
        }
    }

    /**
     * Initialize Book Category dropdown
     */
    private void fillBookType() {
        Connection con = null;
        BookType bookType = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = bookTypeDao.list(con, new BookType());
            while (rs.next()) {
                bookType = new BookType();
                bookType.setId(rs.getInt("id"));
                bookType.setBookTypeName(rs.getString("bookTypeName"));
                this.bookTypeJcb.addItem(bookType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
