import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddAdmin extends JFrame implements ActionListener {
    private static final String JDBCusername = "root";
    private static final String JDBCpassword = "abhi74151";
    private static final String JDBCurl = "jdbc:mysql://localhost:3306/library";

    JLabel userID;
    JLabel name;
    JLabel password;
    JLabel contact;
    JTextField UserID;
    JTextField Username;
    JTextField Password;
    JTextField Contact;

    JButton submit;
    JButton cancel;
    JButton clear;

    AddAdmin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Add Admin");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        userID = new JLabel("User ID");
        name = new JLabel("Name");
        password = new JLabel("Password");
        contact = new JLabel("Contact");
        submit = new JButton("Submit");
        cancel = new JButton("Cancel");
        clear = new JButton("Clear");

        UserID = new JTextField(10);
        Username = new JTextField(10);
        Password = new JTextField(10);
        Contact = new JTextField(10);

        userID.setBounds(50, 50, 100, 30);       // User ID label
        UserID.setBounds(150, 50, 200, 30);      // User ID text field

        name.setBounds(50, 100, 100, 30);        // Name label
        Username.setBounds(150, 100, 200, 30);   // Name text field

        password.setBounds(50, 150, 100, 30);    // Password label
        Password.setBounds(150, 150, 200, 30);   // Password text field

        contact.setBounds(50, 200, 100, 30);     // Contact label
        Contact.setBounds(150, 200, 200, 30);    // Contact text field

        submit.setBounds(50, 300, 100, 40);     // Submit button
        cancel.setBounds(200, 300, 100, 40);     // Cancel button
        clear.setBounds(350, 300, 100, 40);

        submit.setFocusable(false);
        cancel.setFocusable(false);
        clear.setFocusable(false);
        submit.addActionListener(this);
        cancel.addActionListener(this);
        clear.addActionListener(this);

        add(userID);
        add(name);
        add(password);
        add(contact);
        add(UserID);
        add(Username);
        add(Password);
        add(Contact);
        add(submit);
        add(cancel);
        add(clear);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String id = UserID.getText();
            String name = Username.getText();
            String password = Password.getText();
            String contact = Contact.getText();

            if (id.isEmpty() || name.isEmpty() || password.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fields cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!name.matches("^[A-Za-z]+$")) {
                JOptionPane.showMessageDialog(null, "Name contains invalid characters", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!contact.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null, "Invalid contact number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!checkValidity(id)) {
                JOptionPane.showMessageDialog(null, "ID already exists", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Connection conn = DriverManager.getConnection(JDBCurl, JDBCusername, JDBCpassword);
                PreparedStatement ps = conn.prepareStatement("insert into admin values(?,?,?,?)");
                ps.setString(1, id);
                ps.setString(2, name);
                ps.setString(3, password);
                ps.setString(4, contact);

                int resultSet = ps.executeUpdate();
                if (resultSet > 0) {
                    JOptionPane.showMessageDialog(null, "Admin added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Entries not added successfully", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource() == cancel) {
            dispose();
            new LoginPage();
        }
        else if (e.getSource() == clear) {
            UserID.setText("");
            Username.setText("");
            Password.setText("");
            Contact.setText("");
        }
    }

    private boolean checkValidity(String id) {
        try {
            Connection conn = DriverManager.getConnection(JDBCurl, JDBCusername, JDBCpassword);
            Statement stmt = conn.createStatement();
            String query = "Select * from admin";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String ID = rs.getString(1);
                if (ID.equals(id)) {
                    return false;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
