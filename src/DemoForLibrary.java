import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DemoForLibrary extends JFrame implements ActionListener {
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

    DemoForLibrary() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Add Books");
        setLayout(null);
        setSize(500, 500); // Set size for the frame

        // Initialize labels
        BookID = new JLabel("Book ID");
        BookID.setBounds(50, 50, 100, 30); // x, y, width, height

        Category = new JLabel("Category");
        Category.setBounds(50, 100, 100, 30);

        Name = new JLabel("Name");
        Name.setBounds(50, 150, 100, 30);

        Author = new JLabel("Author");
        Author.setBounds(50, 200, 100, 30);

        Copies = new JLabel("Copies");
        Copies.setBounds(50, 250, 100, 30);

        // Initialize text fields
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
        addBook.setBounds(160, 300, 100, 30);
        addBook.addActionListener(this);

        // Add components to the frame
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

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Logic for handling events will go here
    }

    public static void main(String[] args) {
        new DemoForLibrary();
    }
}
