import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteAdmin extends JFrame implements ActionListener {
    String USERNAME;
    private static final String username = "root";
    private static final String password = "abhi74151";
    private static final String url = "jdbc:mysql://localhost:3306/library";


    JLabel labelUserID;
    JTextField fieldUserID;

    JButton delete;

    JButton backButton;
    JButton homeButton;

    DeleteAdmin(String Username) {
        USERNAME = Username;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Delete Books");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        labelUserID = new JLabel("User ID");
        labelUserID.setBounds(50, 50, 100, 30);

        fieldUserID = new JTextField();
        fieldUserID.setBounds(150, 50, 200, 30);

        delete = new JButton("Delete");
        delete.setBounds(150, 150, 100, 30);
        delete.setFocusable(false);
        delete.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(50, 200, 100, 30);
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        homeButton = new JButton("Home");
        homeButton.setBounds(250, 200, 100, 30);
        homeButton.setFocusable(false);
        homeButton.addActionListener(this);


        add(labelUserID);
        add(fieldUserID);
        add(delete);
        add(backButton);
        add(homeButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose();
            new EditAdmin(USERNAME);
        }
        else if (e.getSource() == homeButton) {
            dispose();
            new LoginPage();
        }
        else if (e.getSource() == delete) {
            String deleteID = fieldUserID.getText();

            if (deleteID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fields cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement stm = conn.createStatement();
                String query = "delete from admin where userID = '" + deleteID + "'";
                int resultSet = stm.executeUpdate(query);
                if (resultSet > 0) {
                    dispose();
                    JOptionPane.showMessageDialog(null, "Book deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new DashBoard(USERNAME);
                }
                else {
                    JOptionPane.showMessageDialog(null, "User ID does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (SQLException et) {
                et.printStackTrace();
            }
        }
    }
}
