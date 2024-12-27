import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {
    private static final String JDBCusername = "root";
    private static final String JDBCpassword = "abhi74151";
    private static final String JDBCurl = "jdbc:mysql://localhost:3306/library";

    JLabel username, password;
    JTextField user, pass;
    JButton login;
    JButton signup;
    LoginPage() {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);
//        setBounds(100, 100, 450, 300);

        username = new JLabel("Username");
        username.setBounds(100, 100, 100, 30);
        password = new JLabel("Password");
        password.setBounds(100, 150, 100, 30);

        user = new JTextField(10);
        user.setBounds(210, 100, 150, 30);
        pass = new JTextField(10);
        pass.setBounds(210, 150, 150, 30);

        login = new JButton("Login");
        login.setBounds(210, 200, 150, 30);
        login.setFocusable(false);
        login.addActionListener(this);

        signup = new JButton("Sign Up");
        signup.setBounds(210, 250, 150, 30);
        signup.setFocusable(false);
        signup.addActionListener(this);

        add(username);
        add(password);
        add(user);
        add(pass);
        add(login);
        add(signup);

        setVisible(true);
    }

    private void Login() {
        String username = user.getText();
        String password = pass.getText();
        boolean isValidLogin = false;
        try {
            Connection conn = DriverManager.getConnection(JDBCurl, JDBCusername, JDBCpassword);
            Statement stmt = conn.createStatement();
            String query = "select * from admin";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String adminPass = rs.getString("password");
                String adminUser = rs.getString("userID");
                String adminName = rs.getString("name");

                if (username.equals(adminUser) && password.equals(adminPass)) {
                    dispose();
                    DashBoard dash = new DashBoard(adminName);
                    isValidLogin = true;
                    break;
                }
            }
            if (!isValidLogin) {
                JOptionPane.showMessageDialog(null, "Invalid username or password", "Invalid", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String username = user.getText();
            String password = pass.getText();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username or Password is Empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Login();
        }
        else if (e.getSource() == signup) {
            dispose();
            new AddAdmin();
        }
    }
}
