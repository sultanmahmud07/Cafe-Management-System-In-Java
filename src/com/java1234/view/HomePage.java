package com.java1234.view;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    private String email;

    public HomePage(String userEmail) {
        this.email = userEmail;
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        getContentPane().setLayout(null);

        // Top Navigation Buttons
        int topY = 20;
        int gapX = 20;
        int startX = 20;

        JButton btnLogout = new JButton("Logout");
        btnLogout.setIcon(new ImageIcon("src/images/logout.png"));
        btnLogout.setBounds(startX, topY, 130, 40);
        btnLogout.addActionListener(e -> {
            int a = JOptionPane.showConfirmDialog(null, "Do you really want to Logout", "Select", JOptionPane.YES_NO_OPTION);
            if (a == 0) {
                setVisible(false);
                new LoginPage().setVisible(true);
            }
        });
        getContentPane().add(btnLogout);

        startX += 130 + gapX;
        JButton btnPlaceOrder = new JButton("Place Order");
        btnPlaceOrder.setIcon(new ImageIcon("src/images/place order.png"));
        btnPlaceOrder.setBounds(startX, topY, 150, 40);
        btnPlaceOrder.addActionListener(e -> new PlaceOrderPage(email).setVisible(true));
        getContentPane().add(btnPlaceOrder);

        startX += 150 + gapX;
        JButton btnViewBill = new JButton("View Bill & Order Placed Details");
        btnViewBill.setIcon(new ImageIcon("src/images/View Bills & Order Placed Details.png"));
        btnViewBill.setBounds(startX, topY, 300, 40);
        btnViewBill.addActionListener(e -> new ViewBillOrderPlacedDetailsPage().setVisible(true));
        getContentPane().add(btnViewBill);

        startX += 300 + gapX;
        JButton btnChangePassword = new JButton("Change Password");
        btnChangePassword.setIcon(new ImageIcon("src/images/change Password.png"));
        btnChangePassword.setBounds(startX, topY, 200, 40);
        btnChangePassword.addActionListener(e -> new ChangePasswordPage(email).setVisible(true));
        getContentPane().add(btnChangePassword);

        startX += 200 + gapX;
        JButton btnChangeSq = new JButton("Change Security Question");
        btnChangeSq.setIcon(new ImageIcon("src/images/change Security Question.png"));
        btnChangeSq.setBounds(startX, topY, 250, 40);
        btnChangeSq.addActionListener(e -> new ChangeSecurityQuestionPage(email).setVisible(true));
        getContentPane().add(btnChangeSq);

        startX += 250 + gapX;
        JButton btnExit = new JButton("Exit");
        btnExit.setIcon(new ImageIcon("src/images/exit.png"));
        btnExit.setBounds(startX, topY, 100, 40);
        btnExit.addActionListener(e -> {
            int a = JOptionPane.showConfirmDialog(null, "Do you really want to Close Application", "Select", JOptionPane.YES_NO_OPTION);
            if (a == 0) {
                System.exit(0);
            }
        });
        getContentPane().add(btnExit);

        // Bottom Admin Buttons
        if (email != null && email.equals("admin@gmail.com")) {
            int bottomY = 700; // rough bottom coordinate, adjust as necessary
            int botStartX = 100;
            int botGapX = 20;

            JButton btnManageCat = new JButton("Manage Category");
            btnManageCat.setIcon(new ImageIcon("src/images/category.png"));
            btnManageCat.setBounds(botStartX, bottomY, 200, 40);
            btnManageCat.addActionListener(e -> new ManageCategoryPage().setVisible(true));
            getContentPane().add(btnManageCat);

            botStartX += 200 + botGapX;
            JButton btnNewProduct = new JButton("New Product");
            btnNewProduct.setIcon(new ImageIcon("src/images/new product.png"));
            btnNewProduct.setBounds(botStartX, bottomY, 180, 40);
            btnNewProduct.addActionListener(e -> new NewProductPage().setVisible(true));
            getContentPane().add(btnNewProduct);

            botStartX += 180 + botGapX;
            JButton btnViewEditDelProd = new JButton("View, Edit & Delete Product");
            btnViewEditDelProd.setIcon(new ImageIcon("src/images/view edit delete product.png"));
            btnViewEditDelProd.setBounds(botStartX, bottomY, 250, 40);
            btnViewEditDelProd.addActionListener(e -> new ViewEditDeleteProductPage().setVisible(true));
            getContentPane().add(btnViewEditDelProd);

            botStartX += 250 + botGapX;
            JButton btnVerifyUsers = new JButton("Verify Users");
            btnVerifyUsers.setIcon(new ImageIcon("src/images/verify users.png"));
            btnVerifyUsers.setBounds(botStartX, bottomY, 180, 40);
            btnVerifyUsers.addActionListener(e -> new VerifyUsersPage().setVisible(true));
            getContentPane().add(btnVerifyUsers);
        }

        // Background Image
        JLabel background = new JLabel(new ImageIcon("src/images/home-background-image.png"));
        background.setBounds(0, 0, 1366, 768);
        getContentPane().add(background);
    }
}
