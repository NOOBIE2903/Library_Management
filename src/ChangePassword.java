import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangePassword extends JFrame implements ActionListener {
    String USERNAME;
    private static final String JDBCusername = "root";
    private static final String JDBCpassword = "abhi74151";
    private static final String JDBCurl = "jdbc:mysql://localhost:3306/library";

    JLabel labelPass;
//    JPasswordField oldPassword;
    JTextField textPass;

    JButton back;
    JButton reset;
    JButton home;

    ChangePassword(String USERNAME) {
        this.USERNAME = USERNAME;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Change Password");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        labelPass = new JLabel("Enter Password");
        textPass = new JTextField(10);
        back = new JButton("Back");
        reset = new JButton("Reset");
        home = new JButton("Home");

        labelPass.setBounds(50, 50, 150, 30);      // Label for "Enter Password"
        textPass.setBounds(150, 50, 200, 30);      // Text field for password input
        back.setBounds(50, 200, 100, 40);          // Back button
        reset.setBounds(150, 200, 100, 40);        // Reset button
        home.setBounds(250, 200, 100, 40);         // Home button
        // Home button

        back.addActionListener(this);
        back.setFocusable(false);
        reset.addActionListener(this);
        reset.setFocusable(false);
        home.addActionListener(this);
        home.setFocusable(false);

        add(labelPass);
        add(textPass);
        add(back);
        add(reset);
        add(home);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {
            String pass = textPass.getText();
            if (pass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Field cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Connection conn = DriverManager.getConnection(JDBCurl, JDBCusername, JDBCpassword);
                Statement stmt = conn.createStatement();
                String query = "update admin set password = '" + pass + "' where name = '" + USERNAME + "'";
                int rst = stmt.executeUpdate(query);
                if (rst > 0) {
                    JOptionPane.showMessageDialog(null, "Password Changed", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Password Not Changed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource() == back) {
            dispose();
            new EditAdmin(USERNAME);
        }
        else if (e.getSource() == home) {
            dispose();
            new LoginPage();;
        }
    }
}
