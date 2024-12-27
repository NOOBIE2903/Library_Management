import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BookDetails2 extends JFrame implements ActionListener {
    String USERNAME;

    private static final String username = "root";
    private static final String password = "abhi74151";
    private static final String url = "jdbc:mysql://localhost:3306/library";

    JTextField fieldID;
    JTextField fieldName;
    JTextField fieldAuthor;
    JTextField fieldCategory;
    JTextField fieldCopies;
    JLabel labelID;
    JLabel labelName;
    JLabel labelAuthor;
    JLabel labelCategory;
    JLabel labelCopies;

    JButton backButton;
    JButton homeButton;


    BookDetails2(String BookID, String Username){
        USERNAME = Username;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Book Details");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        fieldID = new JTextField();
        fieldID.setEditable(false);
        fieldName = new JTextField();
        fieldName.setEditable(false);
        fieldAuthor = new JTextField();
        fieldAuthor.setEditable(false);
        fieldCategory = new JTextField();
        fieldCategory.setEditable(false);
        fieldCopies = new JTextField();
        fieldCopies.setEditable(false);

        labelID = new JLabel("ID");
        labelCategory = new JLabel("Category");
        labelName = new JLabel("Name");
        labelAuthor = new JLabel("Author");
        labelCopies = new JLabel("Copies");

        backButton = new JButton("Back");
        homeButton = new JButton("Home");

        int labelWidth = 100; // Label width
        int labelHeight = 30; // Label height
        int fieldWidth = 250; // Field width (to accommodate "Kathi Sierra Bert Bates")
        int fieldHeight = 30; // Field height
        int spacing = 20; // Vertical spacing between components

        int xLabel = 50; // X-coordinate for labels
        int xField = xLabel + labelWidth + 10; // X-coordinate for text fields
        int yStart = 50; // Initial Y-coordinate for the first component

        labelID.setBounds(50, 50, 100, 30);
        fieldID.setBounds(160, 50, 250, 30);

        labelName.setBounds(50, 100, 100, 30);
        fieldName.setBounds(160, 100, 250, 30);

        labelAuthor.setBounds(50, 150, 100, 30);
        fieldAuthor.setBounds(160, 150, 250, 30);

        labelCategory.setBounds(50, 200, 100, 30);
        fieldCategory.setBounds(160, 200, 250, 30);

        labelCopies.setBounds(50, 250, 100, 30);
        fieldCopies.setBounds(160, 250, 250, 30);

// Hardcoded bounds for buttons
        backButton.setBounds(100, 350, 100, 30);
        homeButton.setBounds(260, 350, 100, 30);

        backButton.addActionListener(this);
        homeButton.addActionListener(this);

        backButton.setFocusable(false);
        homeButton.setFocusable(false);

        add(fieldID);
        add(fieldName);
        add(fieldAuthor);
        add(fieldCategory);
        add(fieldCopies);
        add(labelID);
        add(labelCategory);
        add(labelName);
        add(labelAuthor);
        add(labelCopies);

        add(backButton);
        add(homeButton);

        getInformation(BookID);

        setVisible(true);
    }

    private void getInformation(String BookID){
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "Select * from Books where BookID = '" + BookID + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String category = rs.getString(2);
                String name = rs.getString(3);
                String author = rs.getString(4);
                int copies = rs.getInt(5);

                fieldID.setText(BookID);
                fieldName.setText(name);
                fieldAuthor.setText(author);
                fieldCategory.setText(category);
                fieldCopies.setText(String.valueOf(copies));
            }
        }
        catch (SQLException et) {
            et.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose();
            new BookDetails(USERNAME);
        }
        else if (e.getSource() == homeButton) {
            dispose();
            new LoginPage();
        }
    }
}
