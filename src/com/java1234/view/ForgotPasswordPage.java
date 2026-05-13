package com.java1234.view;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import com.java1234.dao.UserDao;
import com.java1234.model.User;
import com.java1234.util.DbUtil;

public class ForgotPasswordPage extends JFrame {

    private JTextField emailTxt;
    private JTextField sqTxt;
    private JTextField answerTxt;
    private JPasswordField newPasswordTxt;
    
    private DbUtil dbUtil = new DbUtil();
    private UserDao userDao = new UserDao();

    public ForgotPasswordPage() {
        setTitle("Forgot Password");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen

        // Create responsive background panel
        JPanel bgPanel = new JPanel() {
            Image bgImage = new ImageIcon("src/images/first page background.PNG").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) {
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        bgPanel.setLayout(new GridBagLayout());
        setContentPane(bgPanel);

        // Form Panel (Centered)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setPreferredSize(new Dimension(800, 600));
        formPanel.setOpaque(false);

        JLabel titleLbl = new JLabel("Cafe Management System");
        titleLbl.setFont(new Font("Tahoma", Font.BOLD, 48));
        titleLbl.setBounds(50, 20, 700, 60);
        titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(titleLbl);

        JLabel pageTitleLbl = new JLabel("Forgot Password?");
        pageTitleLbl.setForeground(Color.RED);
        pageTitleLbl.setFont(new Font("Tahoma", Font.BOLD, 36));
        pageTitleLbl.setBounds(50, 100, 700, 40);
        pageTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(pageTitleLbl);

        int startX = 150;
        int startY = 180;
        int gapY = 50;

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblEmail.setBounds(startX, startY, 150, 30);
        formPanel.add(lblEmail);

        emailTxt = new JTextField();
        emailTxt.setBounds(startX + 180, startY, 250, 30);
        formPanel.add(emailTxt);

        JButton btnSearch = new JButton("Search");
        btnSearch.setIcon(new ImageIcon("src/images/search.png"));
        btnSearch.setBounds(startX + 450, startY, 100, 30);
        btnSearch.addActionListener(e -> searchAction());
        formPanel.add(btnSearch);

        startY += gapY;
        JLabel lblSq = new JLabel("Security Question");
        lblSq.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSq.setBounds(startX, startY, 150, 30);
        formPanel.add(lblSq);

        sqTxt = new JTextField();
        sqTxt.setBounds(startX + 180, startY, 370, 30);
        sqTxt.setEditable(false);
        formPanel.add(sqTxt);

        startY += gapY;
        JLabel lblAnswer = new JLabel("Answer");
        lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAnswer.setBounds(startX, startY, 150, 30);
        formPanel.add(lblAnswer);

        answerTxt = new JTextField();
        answerTxt.setBounds(startX + 180, startY, 370, 30);
        formPanel.add(answerTxt);

        startY += gapY;
        JLabel lblNewPwd = new JLabel("New Password");
        lblNewPwd.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewPwd.setBounds(startX, startY, 150, 30);
        formPanel.add(lblNewPwd);

        newPasswordTxt = new JPasswordField();
        newPasswordTxt.setBounds(startX + 180, startY, 370, 30);
        formPanel.add(newPasswordTxt);

        // Buttons
        startY += gapY + 20;
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setIcon(new ImageIcon("src/images/save.png"));
        btnUpdate.setBounds(startX + 180, startY, 100, 30);
        btnUpdate.addActionListener(e -> updateAction());
        formPanel.add(btnUpdate);

        JButton btnClear = new JButton("Clear");
        btnClear.setIcon(new ImageIcon("src/images/clear.png"));
        btnClear.setBounds(startX + 300, startY, 100, 30);
        btnClear.addActionListener(e -> clearAction());
        formPanel.add(btnClear);

        JButton btnExit = new JButton("Exit");
        btnExit.setIcon(new ImageIcon("src/images/exit small.png"));
        btnExit.setBounds(startX + 420, startY, 100, 30);
        btnExit.addActionListener(e -> System.exit(0));
        formPanel.add(btnExit);

        startY += 50;
        JButton btnSignup = new JButton("Signup");
        btnSignup.setBounds(startX + 180, startY, 100, 30);
        btnSignup.addActionListener(e -> {
            setVisible(false);
            new SignupPage().setVisible(true);
        });
        formPanel.add(btnSignup);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(startX + 420, startY, 100, 30);
        btnLogin.addActionListener(e -> {
            setVisible(false);
            new LoginPage().setVisible(true);
        });
        formPanel.add(btnLogin);

        bgPanel.add(formPanel);
    }

    private void searchAction() {
        String email = emailTxt.getText();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Email field is required");
            return;
        }

        Connection con = null;
        try {
            con = dbUtil.getCon();
            User user = userDao.getSecurityQuestion(con, email);
            if (user != null) {
                sqTxt.setText(user.getSecurityQuestion());
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Email");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { dbUtil.closeCon(con); } catch(Exception e){}
        }
    }

    private void updateAction() {
        String email = emailTxt.getText();
        String answer = answerTxt.getText();
        String newPassword = new String(newPasswordTxt.getPassword());

        if (email.isEmpty() || answer.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required");
            return;
        }

        Connection con = null;
        try {
            con = dbUtil.getCon();
            User user = userDao.getSecurityQuestion(con, email);
            if (user != null && user.getAnswer().equals(answer)) {
                userDao.updatePassword(con, email, newPassword);
                JOptionPane.showMessageDialog(null, "Password Updated Successfully");
                setVisible(false);
                new LoginPage().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Answer");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { dbUtil.closeCon(con); } catch(Exception e){}
        }
    }

    private void clearAction() {
        emailTxt.setText("");
        sqTxt.setText("");
        answerTxt.setText("");
        newPasswordTxt.setText("");
    }
}
