import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashBoard extends JFrame implements ActionListener {
    String USERNAME;

    JButton availableBooks;
    JButton addBooks;
    JButton staffInfo;
    JButton removeStaff;
    JButton addStaff;
    JButton editAdmin;
    JButton bookDetails;
    JButton removeBook;

    JLabel userName;

    DashBoard(String username) {
        USERNAME = username;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DashBoard");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        userName = new JLabel("Welcome " + username);
        userName.setBounds(150, 20, 200, 30); // Centered horizontally at the top
        userName.setForeground(Color.CYAN);
        userName.setFont(new Font("MV Boli", Font.BOLD, 20));

        availableBooks = new JButton("Available Books");
        availableBooks.setBounds(50, 80, 150, 30);
        availableBooks.addActionListener(this);
        availableBooks.setFocusable(false);

        addBooks = new JButton("Add Books");
        addBooks.setBounds(250, 80, 150, 30);
        addBooks.addActionListener(this);
        addBooks.setFocusable(false);

        bookDetails = new JButton("Book Details");
        bookDetails.setBounds(50, 130, 150, 30);
        bookDetails.addActionListener(this);
        bookDetails.setFocusable(false);

        removeBook = new JButton("Remove Book");
        removeBook.setBounds(250, 130, 150, 30);
        removeBook.addActionListener(this);
        removeBook.setFocusable(false);

        staffInfo = new JButton("Staff Info");
        staffInfo.setBounds(50, 180, 150, 30);
        staffInfo.addActionListener(this);
        staffInfo.setFocusable(false);

        removeStaff = new JButton("Remove Staff");
        removeStaff.setBounds(250, 180, 150, 30);
        removeStaff.addActionListener(this);
        removeStaff.setFocusable(false);

        addStaff = new JButton("Add Staff");
        addStaff.setBounds(50, 230, 150, 30);
        addStaff.addActionListener(this);
        addStaff.setFocusable(false);

        editAdmin = new JButton("Edit Admin");
        editAdmin.setBounds(250, 230, 150, 30);
        editAdmin.addActionListener(this);
        editAdmin.setFocusable(false);


        add(availableBooks);
        add(addBooks);
        add(bookDetails);
        add(staffInfo);
        add(removeStaff);
        add(addStaff);
        add(editAdmin);
        add(userName);
        add(removeBook);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBooks) {
            dispose();
            new AddBooks(USERNAME);
        }
        else if (e.getSource() == removeBook) {
            dispose();
            new RemoveBook(USERNAME);
        }
        else if (e.getSource() == bookDetails) {
            dispose();
            new BookDetails(USERNAME);
        }
        else if (e.getSource() == availableBooks) {
            dispose();
            new AvailableBooks(USERNAME);
        }
        else if (e.getSource() == addStaff) {
            dispose();
            new AddStaff(USERNAME);
        }
        else if (e.getSource() == removeStaff) {
            dispose();
            new RemoveStaff(USERNAME);
        }
        else if (e.getSource() == editAdmin) {
            dispose();
            new EditAdmin(USERNAME);
        }
    }
}
