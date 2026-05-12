package com.java1234.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

import com.java1234.dao.BookTypeDao;
import com.java1234.model.BookType;
import com.java1234.util.DbUtil;
import com.java1234.util.StringUtil;

public class BookTypeAddInterFrm extends JInternalFrame {
    private JTextField bookTypeNameTxt;
    private JTextArea bookTypeDescTxt;

    private DbUtil dbUtil = new DbUtil();
    private BookTypeDao bookTypeDao = new BookTypeDao();

    /**
     * Create the frame.
     */
    public BookTypeAddInterFrm() {
        setClosable(true);
        setIconifiable(true);
        setTitle("Book Category Adding");
        setBounds(100, 100, 650, 350);

        JLabel lblBookTypeName = new JLabel("Book type name:");

        JLabel lblBookTypeDescription = new JLabel("Book type descripton:");

        bookTypeNameTxt = new JTextField();
        bookTypeNameTxt.setColumns(10);

        bookTypeDescTxt = new JTextArea();

        JButton btnAdd = new JButton("Add");
        btnAdd.setIcon(new ImageIcon(BookTypeAddInterFrm.class.getResource("/images/add.png")));
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookTypeAddActionPerformed(e);
            }
        });

        JButton btnReset = new JButton("Reset");
        btnReset.setIcon(new ImageIcon(BookTypeAddInterFrm.class.getResource("/images/reset.png")));
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetValueActionPerformed(e);
            }
        });

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(47)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(lblBookTypeName)
                                                .addGap(28)
                                                .addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 185,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(btnAdd)
                                                        .addComponent(lblBookTypeDescription))
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(bookTypeDescTxt,
                                                                        GroupLayout.PREFERRED_SIZE, 185,
                                                                        GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addGap(41)
                                                                .addComponent(btnReset)))))
                                .addContainerGap(78, Short.MAX_VALUE)));
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(35)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblBookTypeName)
                                        .addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(27)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblBookTypeDescription)
                                        .addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 100,
                                                GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnAdd)
                                        .addComponent(btnReset))
                                .addGap(24)));
        getContentPane().setLayout(groupLayout);
    }

    /**
     * Handle Add Action
     * 
     * @param e
     */
    private void bookTypeAddActionPerformed(ActionEvent e) {
        String bookTypeName = this.bookTypeNameTxt.getText();
        String bookTypeDesc = this.bookTypeDescTxt.getText();
        if (StringUtil.isEmpty(bookTypeName)) {
            JOptionPane.showMessageDialog(null, "Book category name cannot be empty!");
            return;
        }
        BookType bookType = new BookType(bookTypeName, bookTypeDesc);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            int n = bookTypeDao.add(con, bookType);
            if (n == 1) {
                JOptionPane.showMessageDialog(null, "Book category added successfully!");
                resetValue();
            } else {
                JOptionPane.showMessageDialog(null, "Book category addition failed!");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Book category addition failed!");
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Handle Reset Action
     * 
     * @param e
     */
    private void resetValueActionPerformed(ActionEvent e) {
        this.resetValue();
    }

    private void resetValue() {
        this.bookTypeNameTxt.setText("");
        this.bookTypeDescTxt.setText("");
    }
}
