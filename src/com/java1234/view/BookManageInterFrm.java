package com.java1234.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import com.java1234.dao.BookDao;
import com.java1234.dao.BookTypeDao;
import com.java1234.model.Book;
import com.java1234.model.BookType;
import com.java1234.util.DbUtil;
import com.java1234.util.StringUtil;

public class BookManageInterFrm extends JInternalFrame {
    private JTable bookTable;
    private JTextField s_bookNameTxt;
    private JTextField s_authorTxt;
    private JComboBox s_bookTypeJcb;
    
    private JTextField idTxt;
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
    public BookManageInterFrm() {
        setClosable(true);
        setIconifiable(true);
        setTitle("Book Management");
        setBounds(100, 100, 750, 600);
        
        JScrollPane scrollPane = new JScrollPane();
        
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Search Criteria", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "Form Operation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(30)
                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                        .addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane, Alignment.LEADING)
                        .addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE))
                    .addContainerGap(32, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(25)
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(32, Short.MAX_VALUE))
        );
        
        JLabel lblId = new JLabel("ID:");
        idTxt = new JTextField();
        idTxt.setEditable(false);
        idTxt.setColumns(10);
        
        JLabel lblBookName = new JLabel("Book Name:");
        bookNameTxt = new JTextField();
        bookNameTxt.setColumns(10);
        
        JLabel lblSex = new JLabel("Gender:");
        manJrb = new JRadioButton("Male");
        femaleJrb = new JRadioButton("Female");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(manJrb);
        buttonGroup.add(femaleJrb);
        
        JLabel lblPrice = new JLabel("Price:");
        priceTxt = new JTextField();
        priceTxt.setColumns(10);
        
        JLabel lblAuthor_1 = new JLabel("Author:");
        authorTxt = new JTextField();
        authorTxt.setColumns(10);
        
        JLabel lblBookCategory_1 = new JLabel("Category:");
        bookTypeJcb = new JComboBox();
        
        JLabel lblDescription = new JLabel("Description:");
        bookDescTxt = new JTextArea();
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setIcon(new ImageIcon(BookManageInterFrm.class.getResource("/images/edit.png")));
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookUpdateActionPerformed(e);
            }
        });
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.setIcon(new ImageIcon(BookManageInterFrm.class.getResource("/images/delete.png")));
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookDeleteActionPerformed(e);
            }
        });
        
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGap(26)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addComponent(lblDescription)
                            .addGap(18)
                            .addComponent(bookDescTxt))
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_panel_1.createSequentialGroup()
                                    .addComponent(lblId)
                                    .addGap(18)
                                    .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                    .addGap(30)
                                    .addComponent(lblBookName))
                                .addGroup(gl_panel_1.createSequentialGroup()
                                    .addComponent(lblPrice)
                                    .addGap(18)
                                    .addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                                    .addGap(25)
                                    .addComponent(lblAuthor_1)))
                            .addGap(18)
                            .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
                                .addGroup(gl_panel_1.createSequentialGroup()
                                    .addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                                    .addGap(28)
                                    .addComponent(lblSex))
                                .addGroup(gl_panel_1.createSequentialGroup()
                                    .addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblBookCategory_1)))
                            .addGap(18)
                            .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_panel_1.createSequentialGroup()
                                    .addComponent(manJrb)
                                    .addGap(18)
                                    .addComponent(femaleJrb))
                                .addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(84, Short.MAX_VALUE))
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGap(242)
                    .addComponent(btnUpdate)
                    .addGap(54)
                    .addComponent(btnDelete)
                    .addContainerGap(222, Short.MAX_VALUE))
        );
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGap(24)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblId)
                        .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblBookName)
                        .addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSex)
                        .addComponent(manJrb)
                        .addComponent(femaleJrb))
                    .addGap(24)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPrice)
                        .addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAuthor_1)
                        .addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblBookCategory_1)
                        .addComponent(bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(27)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblDescription)
                        .addComponent(bookDescTxt, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnUpdate)
                        .addComponent(btnDelete))
                    .addContainerGap())
        );
        panel_1.setLayout(gl_panel_1);
        
        JLabel lblBookName_1 = new JLabel("Book Name:");
        s_bookNameTxt = new JTextField();
        s_bookNameTxt.setColumns(10);
        
        JLabel lblAuthor = new JLabel("Author:");
        s_authorTxt = new JTextField();
        s_authorTxt.setColumns(10);
        
        JLabel lblBookCategory = new JLabel("Category:");
        s_bookTypeJcb = new JComboBox();
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setIcon(new ImageIcon(BookManageInterFrm.class.getResource("/images/search.png")));
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookSearchActionPerformed(e);
            }
        });
        
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(21)
                    .addComponent(lblBookName_1)
                    .addGap(18)
                    .addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(lblAuthor)
                    .addGap(18)
                    .addComponent(s_authorTxt, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(lblBookCategory)
                    .addGap(18)
                    .addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(btnSearch)
                    .addContainerGap(19, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(16)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblBookName_1)
                        .addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAuthor)
                        .addComponent(s_authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblBookCategory)
                        .addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch))
                    .addContainerGap(14, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        
        bookTable = new JTable();
        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bookTableMousePressed(e);
            }
        });
        bookTable.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "ID", "Book Name", "Author", "Gender", "Price", "Description", "Category"
            }
        ) {
            boolean[] columnEditables = new boolean[] {
                false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        scrollPane.setViewportView(bookTable);
        getContentPane().setLayout(groupLayout);
        
        this.fillBookType("search");
        this.fillBookType("modify");
        this.fillTable(new Book());
    }

    /**
     * Book Delete Action
     * @param e
     */
    private void bookDeleteActionPerformed(ActionEvent e) {
        String id = idTxt.getText();
        if (StringUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "Please select the record to delete!");
            return;
        }
        int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?");
        if (n == 0) {
            Connection con = null;
            try {
                con = dbUtil.getCon();
                int deleteNum = bookDao.delete(con, id);
                if (deleteNum == 1) {
                    JOptionPane.showMessageDialog(null, "Deleted successfully!");
                    this.resetValue();
                    this.fillTable(new Book());
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
     * Book Update Action
     * @param e
     */
    private void bookUpdateActionPerformed(ActionEvent e) {
        String id = this.idTxt.getText();
        if (StringUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "Please select the record to modify!");
            return;
        }
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
        
        Book book = new Book(Integer.parseInt(id), bookName, author, sex, Float.parseFloat(price), bookTypeId, bookDesc);
        
        Connection con = null;
        try {
            con = dbUtil.getCon();
            int modifyNum = bookDao.update(con, book);
            if (modifyNum == 1) {
                JOptionPane.showMessageDialog(null, "Modified successfully!");
                this.resetValue();
                this.fillTable(new Book());
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
     * Initialize Book Category dropdowns
     * @param type "search" or "modify"
     */
    private void fillBookType(String type) {
        Connection con = null;
        BookType bookType = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = bookTypeDao.list(con, new BookType());
            if ("search".equals(type)) {
                bookType = new BookType();
                bookType.setBookTypeName("Please select...");
                bookType.setId(-1);
                this.s_bookTypeJcb.addItem(bookType);
            }
            while (rs.next()) {
                bookType = new BookType();
                bookType.setId(rs.getInt("id"));
                bookType.setBookTypeName(rs.getString("bookTypeName"));
                if ("search".equals(type)) {
                    this.s_bookTypeJcb.addItem(bookType);
                } else if ("modify".equals(type)) {
                    this.bookTypeJcb.addItem(bookType);
                }
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
     * Form auto-fill upon row selection
     * @param e
     */
    private void bookTableMousePressed(MouseEvent e) {
        int row = this.bookTable.getSelectedRow();
        this.idTxt.setText((String) bookTable.getValueAt(row, 0));
        this.bookNameTxt.setText((String) bookTable.getValueAt(row, 1));
        this.authorTxt.setText((String) bookTable.getValueAt(row, 2));
        String sex = (String) bookTable.getValueAt(row, 3);
        if ("Male".equals(sex)) {
            this.manJrb.setSelected(true);
        } else if ("Female".equals(sex)) {
            this.femaleJrb.setSelected(true);
        }
        this.priceTxt.setText((String) bookTable.getValueAt(row, 4));
        this.bookDescTxt.setText((String) bookTable.getValueAt(row, 5));
        
        String bookTypeName = (String) this.bookTable.getValueAt(row, 6);
        int n = this.bookTypeJcb.getItemCount();
        for (int i = 0; i < n; i++) {
            BookType item = (BookType) this.bookTypeJcb.getItemAt(i);
            if (item.getBookTypeName().equals(bookTypeName)) {
                this.bookTypeJcb.setSelectedIndex(i);
                break;
            }
        }
    }

    /**
     * Book Search Action
     * @param e
     */
    private void bookSearchActionPerformed(ActionEvent e) {
        String bookName = this.s_bookNameTxt.getText();
        String author = this.s_authorTxt.getText();
        BookType bookType = (BookType) this.s_bookTypeJcb.getSelectedItem();
        int bookTypeId = bookType.getId();
        Book book = new Book();
        book.setBookName(bookName);
        book.setAuthor(author);
        book.setBookTypeId(bookTypeId);
        this.fillTable(book);
    }
    
    /**
     * Initialize table data
     * @param book
     */
    private void fillTable(Book book) {
        DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
        dtm.setRowCount(0); // clear
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = bookDao.list(con, book);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("bookName"));
                v.add(rs.getString("author"));
                v.add(rs.getString("sex"));
                v.add(rs.getString("price"));
                v.add(rs.getString("bookDesc"));
                v.add(rs.getString("bookTypeName"));
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
        this.bookNameTxt.setText("");
        this.authorTxt.setText("");
        this.priceTxt.setText("");
        this.manJrb.setSelected(true);
        this.bookDescTxt.setText("");
        if (this.bookTypeJcb.getItemCount() > 0) {
            this.bookTypeJcb.setSelectedIndex(0);
        }
    }
}
