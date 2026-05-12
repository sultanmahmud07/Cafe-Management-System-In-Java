package com.java1234.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.java1234.dao.UserDao;
import com.java1234.model.User;
import com.java1234.util.DbUtil;
import com.java1234.util.StringUtil;

public class LogOnFrm extends JFrame {

    private JPanel contentPane;
    private JTextField userNameTxt;
    private JPasswordField passwordTxt;

    private DbUtil dbUtil = new DbUtil();
    private UserDao userDao = new UserDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LogOnFrm frame = new LogOnFrm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LogOnFrm() {
        setTitle("Administrator Login");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 380);
        setLocationRelativeTo(null); // Center the window

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblSystemName = new JLabel("Library Management System");
        lblSystemName.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/logo.png")));
        lblSystemName.setFont(new Font("Dialog", Font.BOLD, 22));

        JLabel lblUserName = new JLabel("User Name:");
        lblUserName.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/userName.png")));

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/password.png")));

        userNameTxt = new JTextField();
        userNameTxt.setColumns(10);

        passwordTxt = new JPasswordField();

        JButton btnLogin = new JButton("Login in");
        btnLogin.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/login.png")));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginActionPerformed(e);
            }
        });

        JButton btnReset = new JButton("Reset");
        btnReset.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/reset.png")));
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetValueActionPerformed(e);
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(100)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblSystemName)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(lblUserName)
                                                        .addComponent(lblPassword)
                                                        .addComponent(btnLogin))
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addGap(30)
                                                                .addGroup(gl_contentPane
                                                                        .createParallelGroup(Alignment.LEADING, false)
                                                                        .addComponent(passwordTxt)
                                                                        .addComponent(userNameTxt,
                                                                                GroupLayout.DEFAULT_SIZE, 180,
                                                                                Short.MAX_VALUE)))
                                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addGap(50)
                                                                .addComponent(btnReset)))))
                                .addContainerGap(59, Short.MAX_VALUE)));
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(38)
                                .addComponent(lblSystemName)
                                .addGap(45)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblUserName)
                                        .addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(31)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblPassword)
                                        .addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(40)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnLogin)
                                        .addComponent(btnReset))
                                .addContainerGap(47, Short.MAX_VALUE)));
        contentPane.setLayout(gl_contentPane);
    }

    /**
     * Handle Login Action
     * 
     * @param e
     */
    private void loginActionPerformed(ActionEvent e) {
        String userName = this.userNameTxt.getText();
        String password = new String(this.passwordTxt.getPassword());
        if (StringUtil.isEmpty(userName)) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty!");
            return;
        }
        if (StringUtil.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty!");
            return;
        }
        User user = new User(userName, password);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            User currentUser = userDao.login(con, user);
            if (currentUser != null) {
                dispose(); // Close login frame
                new MainFrm().setVisible(true); // Open main frame
            } else {
                JOptionPane.showMessageDialog(null, "Username or password incorrect!");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Login failed due to system error!");
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
        this.userNameTxt.setText("");
        this.passwordTxt.setText("");
    }
}
