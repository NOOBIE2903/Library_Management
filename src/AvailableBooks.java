import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AvailableBooks extends JFrame implements ActionListener {
    String USERNAME;

    private static final String username = "root";
    private static final String password = "abhi74151";
    private static final String url = "jdbc:mysql://localhost:3306/library";

    JTable table;
    JScrollPane scrollPane;
    JButton homeButton;
    JButton backButton;

    AvailableBooks(String Username) {
        this.USERNAME = Username;
        setTitle("Available Books");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null); // Center the window

        // Create the JTable and set its model
        String[] columnNames = {"Book ID", "Category", "Name", "Author", "Copies"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };


        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setFillsViewportHeight(true);

        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);

        // Fetch books data from the database and populate the table
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM books";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String bookID = rs.getString("BookID");
                String category = rs.getString("category");
                String name = rs.getString("name");
                String author = rs.getString("author");
                int copies = rs.getInt("copies");

                // Add row to the table model
                model.addRow(new Object[]{bookID, category, name, author, copies});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add the table inside a JScrollPane
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Initialize Home and Back buttons
        homeButton = new JButton("Home");
        homeButton.setBounds(50, 300, 100, 30);
        homeButton.setFocusable(false);
        homeButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(200, 300, 100, 30);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        // Set layout to null for manual positioning of components
//        setLayout(null);

        // Add buttons to the frame
//        add(homeButton, BorderLayout.SOUTH, 1);
//        add(backButton, BorderLayout.EAST);

        // Show the window
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeButton) {
            dispose();
            new LoginPage(); // Open LoginPage when Home is clicked
        } else if (e.getSource() == backButton) {
            dispose();
            new DashBoard(USERNAME); // Open DashBoard when Back is clicked
        }
    }
}
