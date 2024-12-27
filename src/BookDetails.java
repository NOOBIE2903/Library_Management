import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BookDetails extends JFrame implements ActionListener {
    String USERNAME;

    private static final String username = "root";
    private static final String password = "abhi74151";
    private static final String url = "jdbc:mysql://localhost:3306/library";

    JLabel label;
    JButton button;
    JTextField field;

    JButton backButton;
    JButton homeButton;

    BookDetails(String Username) {
        USERNAME = Username;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Book Details");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        label = new JLabel("Book ID");
        label.setBounds(100, 50, 100, 30);

        // Text field for entering the book ID
        field = new JTextField(10);
        field.setBounds(200, 50, 200, 30);

        // "Find Details" button
        button = new JButton("Find Details");
        button.setBounds(200, 100, 120, 30);
        button.setFocusable(false);

        // "Back" button
        backButton = new JButton("Back");
        backButton.setBounds(100, 200, 120, 30);
        backButton.setFocusable(false);

        // "Home" button
        homeButton = new JButton("Home");
        homeButton.setBounds(280, 200, 120, 30);
        homeButton.setFocusable(false);

        // Adding action listeners
        button.addActionListener(this);
        backButton.addActionListener(this);
        homeButton.addActionListener(this);


        add(backButton);
        add(label);
        add(field);
        add(button);
        add(homeButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeButton) {
            dispose();
            new LoginPage();
        }
        else if (e.getSource() == backButton) {
            dispose();
            new DashBoard(USERNAME);
        }
        else if (e.getSource() == button) {
            String ID = field.getText();
            if (ID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Field cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!ID.matches("B\\d{3}")) {
                JOptionPane.showMessageDialog(null, "ID must be a valid BDD number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement stmt = conn.createStatement();
                String query = "Select * from Books where BookID = '" + ID + "'";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String BookID = rs.getString("BookID");
                    if (BookID.equals(ID)) {
                        dispose();
                        new BookDetails2(BookID, USERNAME);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Book is not available by the ID: " + ID, "Not Found", JOptionPane.ERROR_MESSAGE);
                return;
            }
            catch (SQLException et) {
                et.printStackTrace();
            }
        }
    }
}
