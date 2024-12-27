import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddBooks extends JFrame implements ActionListener {
    String USERNAME;
    private static final String username = "root";
    private static final String password = "abhi74151";
    private static final String url = "jdbc:mysql://localhost:3306/library";


    JLabel BookID;
    JLabel Category;
    JLabel Name;
    JLabel Author;
    JLabel Copies;
    JTextField bookID;
    JTextField bookCategory;
    JTextField bookName;
    JTextField bookAuthor;
    JTextField bookCopies;

    JButton addBook;

    JButton backButton;
    JButton homeButton;

    AddBooks(String Username) {
        USERNAME = Username;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Add Books");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        BookID = new JLabel("Book ID");
        BookID.setBounds(50, 50, 100, 30);
        Category = new JLabel("Category");
        Category.setBounds(50, 100, 100, 30);
        Name = new JLabel("Name");
        Name.setBounds(50, 150, 100, 30);
        Author = new JLabel("Author");
        Author.setBounds(50, 200, 100, 30);
        Copies = new JLabel("Copies");
        Copies.setBounds(50, 250, 100, 30);

        bookID = new JTextField();
        bookID.setBounds(150, 50, 200, 30);
        bookCategory = new JTextField();
        bookCategory.setBounds(150, 100, 200, 30);
        bookName = new JTextField();
        bookName.setBounds(150, 150, 200, 30);
        bookAuthor = new JTextField();
        bookAuthor.setBounds(150, 200, 200, 30);
        bookCopies = new JTextField();
        bookCopies.setBounds(150, 250, 200, 30);

        addBook = new JButton("Add Book");
        addBook.setBounds(150, 300, 100, 30);
        addBook.setFocusable(false);
        addBook.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(50, 350, 100, 30);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        homeButton = new JButton("Home");
        homeButton.setBounds(250, 350, 100, 30);
        homeButton.setFocusable(false);
        homeButton.addActionListener(this);


        add(BookID);
        add(Category);
        add(Name);
        add(Author);
        add(Copies);
        add(bookID);
        add(bookCategory);
        add(bookName);
        add(bookAuthor);
        add(bookCopies);
        add(addBook);
        add(backButton);
        add(homeButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeButton) {
            dispose();
            new LoginPage();
        }
        else if (e.getSource() == backButton) {
            dispose();
            new DashBoard(USERNAME);
        }
        else if (e.getSource() == addBook) {
            String insertID = bookID.getText();
            String insertCategory = bookCategory.getText();
            String insertName = bookName.getText();
            String insertAuthor = bookAuthor.getText();
            String copiesText = bookCopies.getText();

            if (insertID.isEmpty() || insertCategory.isEmpty() || insertName.isEmpty() || insertAuthor.isEmpty() || copiesText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fields cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int insertCopies = Integer.parseInt(copiesText);
            if (insertCopies <= 0) {
                JOptionPane.showMessageDialog(null, "Copies must be a positive integer", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!insertID.matches("B\\d{3}")) {
                JOptionPane.showMessageDialog(null, "ID must be a valid BD number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!insertCategory.matches("^[a-zA-Z0-9]+$")) {
                JOptionPane.showMessageDialog(null, "Category must be a valid category", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!insertName.matches("^[a-zA-Z0-9]+$")) {
                JOptionPane.showMessageDialog(null, "Name must be a valid name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!insertAuthor.matches("[A-Z]")) {
                JOptionPane.showMessageDialog(null, "Author must be a valid author", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement stm = conn.prepareStatement("insert into books (BookID, category, name, author, copies) values(?,?,?,?,?)");
                stm.setString(1, insertID);
                stm.setString(2, insertCategory);
                stm.setString(3, insertName);
                stm.setString(4, insertAuthor);
                stm.setInt(5, insertCopies);
                int resultSet = stm.executeUpdate();

                if (resultSet > 0) {
                    dispose();
                    JOptionPane.showMessageDialog(null, "Book added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new DashBoard(USERNAME);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Book could not be added", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (SQLException et) {
                et.printStackTrace();
            }
        }
    }
}
