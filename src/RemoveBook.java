import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RemoveBook extends JFrame implements ActionListener {
    String USERNAME;
    private static final String username = "root";
    private static final String password = "abhi74151";
    private static final String url = "jdbc:mysql://localhost:3306/library";

    JLabel BookID;
    JTextField bookID;

    JButton deleteBook;

    JButton backButton;
    JButton homeButton;

    RemoveBook(String Username) {
        USERNAME = Username;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Delete Books");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        BookID = new JLabel("Book ID");
        BookID.setBounds(50, 50, 100, 30);

        bookID = new JTextField();
        bookID.setBounds(150, 50, 200, 30);

        deleteBook = new JButton("Delete Book");
        deleteBook.setBounds(150, 150, 100, 30);
        deleteBook.setFocusable(false);
        deleteBook.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(50, 200, 100, 30);
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        homeButton = new JButton("Home");
        homeButton.setBounds(250, 200, 100, 30);
        homeButton.setFocusable(false);
        homeButton.addActionListener(this);


        add(BookID);
        add(bookID);
        add(deleteBook);
        add(backButton);
        add(homeButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose();
            new DashBoard(USERNAME);
        }
        else if (e.getSource() == homeButton) {
            dispose();
            new LoginPage();
        }
        else if (e.getSource() == deleteBook) {
            String deleteID = bookID.getText();

            if (deleteID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fields cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!deleteID.matches("B\\d{3}")) {
                JOptionPane.showMessageDialog(null, "Book is not of the form Bxxx.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement stm = conn.createStatement();
                String query = "delete from books where bookID = '" + deleteID + "'";
                int resultSet = stm.executeUpdate(query);
                if (resultSet > 0) {
                    dispose();
                    JOptionPane.showMessageDialog(null, "Book deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new DashBoard(USERNAME);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Book could not be deleted", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (SQLException et) {
                et.printStackTrace();
            }
        }
    }
}
