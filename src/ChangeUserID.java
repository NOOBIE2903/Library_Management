import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangeUserID extends JFrame implements ActionListener {
    String USERNAME;
    private static final String JDBCusername = "root";
    private static final String JDBCpassword = "abhi74151";
    private static final String JDBCurl = "jdbc:mysql://localhost:3306/library";

    JLabel labelUserID;
    //    JPasswordField oldPassword;
    JTextField textUserID;

    JButton back;
    JButton reset;
    JButton home;

    ChangeUserID(String USERNAME) {
        this.USERNAME = USERNAME;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Change User ID");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        labelUserID = new JLabel("Enter User ID");
        textUserID = new JTextField(10);
        back = new JButton("Back");
        reset = new JButton("Reset");
        home = new JButton("Home");

        labelUserID.setBounds(50, 50, 150, 30);
        textUserID.setBounds(150, 50, 200, 30);
        back.setBounds(50, 200, 100, 40);
        reset.setBounds(150, 200, 100, 40);
        home.setBounds(250, 200, 100, 40);

        back.addActionListener(this);
        back.setFocusable(false);
        reset.addActionListener(this);
        reset.setFocusable(false);
        home.addActionListener(this);
        home.setFocusable(false);

        add(labelUserID);
        add(textUserID);
        add(back);
        add(reset);
        add(home);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {
            String userID = textUserID.getText();
            if (userID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Field cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Connection conn = DriverManager.getConnection(JDBCurl, JDBCusername, JDBCpassword);
                Statement stmt = conn.createStatement();
                String query = "update admin set userID = '" + userID + "' where name = '" + USERNAME + "'";
                int rst = stmt.executeUpdate(query);
                if (rst > 0) {
                    JOptionPane.showMessageDialog(null, "User ID Changed", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "User ID Not Changed", "Error", JOptionPane.ERROR_MESSAGE);
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
