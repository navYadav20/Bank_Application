package guis;

import db_objs.JDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterGui extends BaseFrame{

    public RegisterGui() {
        super("Banking App Register");
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
        passwordLabel.setBounds(20,220,getWidth()-50,24);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN,20));
        add(passwordLabel);
        // create password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20,260,getWidth()-50, 40);
        passwordField.setFont(new Font("Dialog", Font.PLAIN,28));
        add(passwordField);

        // create re-type password label
        JLabel rePasswordLabel = new JLabel("Re-type Password:");
        // getWidth() returns us the width of our frame which is about 420
        rePasswordLabel.setBounds(20,320,getWidth()-50,40);
        rePasswordLabel.setFont(new Font("Dialog", Font.PLAIN,20));
        add(rePasswordLabel);
        // create re-password field
        JPasswordField rePasswordField = new JPasswordField();
        rePasswordField.setBounds(20,360,getWidth()-50, 40);
        rePasswordField.setFont(new Font("Dialog", Font.PLAIN,28));
        add(rePasswordField);



        // create register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(20,460,getWidth()-50,40);
        registerButton.setFont(new Font("Dialog",Font.BOLD,20));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get username
                String username = usernameField.getText();

                // get password
                String password = String.valueOf(passwordField.getPassword());

                // get re password
                String rePassword = String.valueOf(rePasswordField.getPassword());

                // we will need to validate the user input
                if(validateUserInput(username, password, rePassword)){
                    // attempt to register the user to the database
                    if(JDBC.register(username, password)){
                        // register success
                        // dispose of this gui
                        RegisterGui.this.dispose();

                        // launch the login gui
                        LoginGui loginGui = new LoginGui();
                        loginGui.setVisible(true);

                        // create a result dialog
                        JOptionPane.showMessageDialog(loginGui, "Registered Account Successfully!");
                    }else{
                        // register failed
                        JOptionPane.showMessageDialog(RegisterGui.this, "Error: Username already taken");
                    }
                }else{
                    // invalid user input
                    JOptionPane.showMessageDialog(RegisterGui.this,
                            "Error: Username must be at least 6 characters\n" +
                                    "and/or Password must match");
                }
            }
        });
        add(registerButton);

        // create login label
        JLabel loginLabel = new JLabel("<html><a href=\"#\">Have an account? Sign-in Here</a></html>");
        loginLabel.setBounds(0,510,getWidth()-10,30);
        loginLabel.setFont(new Font("Dialog",Font.BOLD,20));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // dispose of this gui
                RegisterGui.this.dispose();

                // launch the login gui
                new LoginGui().setVisible(true);
            }
        });
        add(loginLabel);

    }
    private boolean validateUserInput(String username, String password, String rePassword){
        // all fields must have a value
        if(username.length() == 0 || password.length() == 0 || rePassword.length() == 0) return false;

        // username has to be at least 6 characters long
        if(username.length() < 6) return false;

        // password and repassword must be the same
        if(!password.equals(rePassword)) return false;

        // passes validation
        return true;
    }
}
