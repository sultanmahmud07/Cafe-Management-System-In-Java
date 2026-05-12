package com.java1234.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

import com.java1234.dao.BookDao;
import com.java1234.dao.BookTypeDao;
import com.java1234.model.BookType;
import com.java1234.util.DbUtil;
import com.java1234.util.StringUtil;

public class BookTypeManageInterFrm extends JInternalFrame {
    private JTable bookTypeTable;
    private JTextField s_bookTypeNameTxt;
    private JTextField idTxt;
    private JTextField bookTypeNameTxt;
    private JTextArea bookTypeDescTxt;
    
    private DbUtil dbUtil = new DbUtil();
    private BookTypeDao bookTypeDao = new BookTypeDao();
    private BookDao bookDao = new BookDao();

    /**
     * Create the frame.
     */
    public BookTypeManageInterFrm() {
        setClosable(true);
        setIconifiable(true);
        setTitle("Book Type Management");
        setBounds(100, 100, 870, 669);
        
        JScrollPane scrollPane = new JScrollPane();
        
        JLabel lblBookTypeName = new JLabel("Book Type name:");
        
        s_bookTypeNameTxt = new JTextField();
        s_bookTypeNameTxt.setColumns(10);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/search.png")));
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookTypeSearchActionPerformed(e);
            }
        });
        
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Form Operation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(85)
                    .addComponent(lblBookTypeName)
                    .addGap(18)
                    .addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(btnSearch)
                    .addContainerGap(155, Short.MAX_VALUE))
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(42)
                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                        .addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE))
                    .addContainerGap(42, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(33)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblBookTypeName)
                        .addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch))
                    .addGap(26)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(28, Short.MAX_VALUE))
        );
        
        JLabel lblId = new JLabel("ID:");
        
        idTxt = new JTextField();
        idTxt.setEditable(false);
        idTxt.setColumns(10);
        
        JLabel lblBookTypeName_1 = new JLabel("Book Type Name:");
        
        bookTypeNameTxt = new JTextField();
        bookTypeNameTxt.setColumns(10);
        
        JLabel lblDescription = new JLabel("Description:");
        
        bookTypeDescTxt = new JTextArea();
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/edit.png")));
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookTypeUpdateActionPerformed(e);
            }
        });
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/delete.png")));
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookTypeDeleteActionPerformed(e);
            }
        });
        
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(19)
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addComponent(lblDescription)
                            .addGap(18)
                            .addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_panel.createSequentialGroup()
                            .addComponent(lblId)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                            .addGap(31)
                            .addComponent(lblBookTypeName_1)
                            .addGap(18)
                            .addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(31, Short.MAX_VALUE))
                .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                    .addContainerGap(161, Short.MAX_VALUE)
                    .addComponent(btnUpdate)
                    .addGap(35)
                    .addComponent(btnDelete)
                    .addGap(136))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(21)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblId)
                        .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblBookTypeName_1)
                        .addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblDescription)
                        .addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnUpdate)
                        .addComponent(btnDelete))
                    .addContainerGap())
        );
        panel.setLayout(gl_panel);
        
        bookTypeTable = new JTable();
        bookTypeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bookTypeTableMousePressed(e);
            }
        });
        bookTypeTable.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "ID", "Book Type", "Book Type Description"
            }
        ) {
            boolean[] columnEditables = new boolean[] {
                false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        scrollPane.setViewportView(bookTypeTable);
        getContentPane().setLayout(groupLayout);
        
        this.fillTable(new BookType());
    }

    /**
     * Book Type Delete Action
     * @param e
     */
    private void bookTypeDeleteActionPerformed(ActionEvent e) {
        String id = idTxt.getText();
        if (StringUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "Please select the record to delete");
            return;
        }
        int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?");
        if (n == 0) {
            Connection con = null;
            try {
                con = dbUtil.getCon();
                boolean flag = bookDao.existsBookByBookTypeId(con, id);
                if (flag) {
                    JOptionPane.showMessageDialog(null, "There are books under this category. Cannot delete!");
                    return;
                }
                int deleteNum = bookTypeDao.delete(con, id);
                if (deleteNum == 1) {
                    JOptionPane.showMessageDialog(null, "Deleted successfully!");
                    this.resetValue();
                    this.fillTable(new BookType());
                } else {
                    JOptionPane.showMessageDialog(null, "Delete failed!");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Delete failed!");
            } finally {
                try {
                    dbUtil.closeCon(con);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * Book Type Update Action
     * @param e
     */
    private void bookTypeUpdateActionPerformed(ActionEvent e) {
        String id = idTxt.getText();
        String bookTypeName = bookTypeNameTxt.getText();
        String bookTypeDesc = bookTypeDescTxt.getText();
        if (StringUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "Please select the record to modify");
            return;
        }
        if (StringUtil.isEmpty(bookTypeName)) {
            JOptionPane.showMessageDialog(null, "Book category name cannot be empty");
            return;
        }
        BookType bookType = new BookType(Integer.parseInt(id), bookTypeName, bookTypeDesc);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            int modifyNum = bookTypeDao.update(con, bookType);
            if (modifyNum == 1) {
                JOptionPane.showMessageDialog(null, "Modified successfully!");
                this.resetValue();
                this.fillTable(new BookType());
            } else {
                JOptionPane.showMessageDialog(null, "Modification failed!");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Modification failed!");
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Form auto-fill upon row selection
     * @param e
     */
    private void bookTypeTableMousePressed(MouseEvent e) {
        int row = bookTypeTable.getSelectedRow();
        idTxt.setText((String) bookTypeTable.getValueAt(row, 0));
        bookTypeNameTxt.setText((String) bookTypeTable.getValueAt(row, 1));
        bookTypeDescTxt.setText((String) bookTypeTable.getValueAt(row, 2));
    }

    /**
     * Book Type Search Action
     * @param e
     */
    private void bookTypeSearchActionPerformed(ActionEvent e) {
        String s_bookTypeName = this.s_bookTypeNameTxt.getText();
        BookType bookType = new BookType();
        bookType.setBookTypeName(s_bookTypeName);
        this.fillTable(bookType);
    }

    /**
     * Initialize table data
     * @param bookType
     */
    private void fillTable(BookType bookType) {
        DefaultTableModel dtm = (DefaultTableModel) bookTypeTable.getModel();
        dtm.setRowCount(0); // clear existing data
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = bookTypeDao.list(con, bookType);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("bookTypeName"));
                v.add(rs.getString("bookTypeDesc"));
                dtm.addRow(v);
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
    
    /**
     * Reset form fields
     */
    private void resetValue() {
        this.idTxt.setText("");
        this.bookTypeNameTxt.setText("");
        this.bookTypeDescTxt.setText("");
    }
}
