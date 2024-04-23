package guis;

    // this gui will allow user to login or launch the register gui
    // this extends from the BaseFrame which means we will need to define our own addGuiComponent

import db_objs.JDBC;
import db_objs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGui extends BaseFrame {
    public LoginGui() {
        super("Banking App Login"); //calls the constructor of the superclass("BaseFrame")
    }

    @Override
    protected void addGuiComponents() {
        // create banking app label
        JLabel bankingAppLabel = new JLabel("Banking Application");

        bankingAppLabel.setBounds(0,20,super.getWidth(),  40);

        bankingAppLabel.setFont(new Font("Dialog",Font.BOLD,32));

        bankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // add to GUI
        add(bankingAppLabel);


        // create username Label
        JLabel usernameLabel = new JLabel("Username:");

        // getWidth() returns us the width of our frame which is about 420
        usernameLabel.setBounds(20,120,getWidth()-30,24);

        usernameLabel.setFont(new Font("Dialog", Font.PLAIN,20));
        add(usernameLabel);

        // create username field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(20,160,getWidth()-50, 40);
        usernameField.setFont(new Font("Dialog", Font.PLAIN,28));
        add(usernameField);


        //create password Label
        JLabel passwordLabel = new JLabel("Password:");
        // getWidth() returns us the width of our frame which is about 420
        passwordLabel.setBounds(20,280,getWidth()-50,24);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN,20));
        add(passwordLabel);
        // create password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20,320,getWidth()-50, 40);
        passwordField.setFont(new Font("Dialog", Font.PLAIN,28));
        add(passwordField);

        // create login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(20,460,getWidth()-50,40);
        loginButton.setFont(new Font("Dialog",Font.BOLD,20));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();  // get username
                String password = String.valueOf(passwordField.getPassword()); // get password

                // validate login
                User user = JDBC.validateLogin(username, password);

                // if user is null it means invalid otherwise it is a valid account
                if(user != null){ // means valid login

                    // dispose this gui
                    LoginGui.this.dispose();

                    // launch banking app gui
                    BankingAppGui bankingAppGui = new BankingAppGui(user);
                    bankingAppGui.setVisible(true);

                    // show success dialog
                    JOptionPane.showMessageDialog(bankingAppGui, "Login Successfully!");
                }else{
                    // invalid login
                    JOptionPane.showMessageDialog(LoginGui.this, "Login failed...");
                }
            }
        });
        add(loginButton);

        // create register label
        JLabel registerLabel = new JLabel("<html><a href=\"#\">Don't have an account? Register Here</a></html>");
        registerLabel.setBounds(0,510,getWidth()-10,30);
        registerLabel.setFont(new Font("Dialog",Font.BOLD,20));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //adds an event listener so when clicked it will launch the register GUI
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // dispose of this GUI
                LoginGui.this.dispose();

                //launch the register GUI
                new RegisterGui().setVisible(true);
            }
        });
        add(registerLabel);




    }
}
