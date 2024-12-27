import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddStaff extends JFrame implements ActionListener {
    String USERNAME;
    private static final String JDBCusername = "root";
    private static final String JDBCpassword = "abhi74151";
    private static final String JDBCurl = "jdbc:mysql://localhost:3306/library";


    JLabel staffID;
    JLabel staffName;
    JLabel contact;
    JButton addStaff;
    JTextField ID;
    JTextField Name;
    JTextField Contact;

    JButton back;
    JButton home;
    JButton clear;

    AddStaff (String username) {
        this.USERNAME = username;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Add Staff");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        staffID = new JLabel("Staff ID");
        staffName = new JLabel("Name");
        contact = new JLabel("Contact");

        addStaff = new JButton("Add Staff");

        back = new JButton("Back");
        home = new JButton("Home");
        clear = new JButton("Clear");

        ID = new JTextField(10);
        Name = new JTextField(10);
        Contact = new JTextField(10);

        staffID.setBounds(50, 50, 100, 30);
        ID.setBounds(150, 50, 200, 30);

        staffName.setBounds(50, 100, 100, 30);
        Name.setBounds(150, 100, 200, 30);

        contact.setBounds(50, 150, 100, 30);
        Contact.setBounds(150, 150, 200, 30);

        addStaff.setBounds(150, 200, 150, 40);

        back.setBounds(50, 300, 100, 40);
        home.setBounds(200, 300, 100, 40);
        clear.setBounds(350, 300, 100, 40);

        addStaff.setFocusable(false);
        addStaff.addActionListener(this);

        back.setFocusable(false);
        home.setFocusable(false);
        clear.setFocusable(false);

        back.addActionListener(this);
        home.addActionListener(this);
        clear.addActionListener(this);

        add(staffID);
        add(staffName);
        add(contact);
        add(addStaff);
        add(ID);
        add(Name);
        add(Contact);
        add(back);
        add(home);
        add(clear);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStaff) {
            String id = ID.getText();
            String name = Name.getText();
            String contact = Contact.getText();

            if (id.isEmpty() || name.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fields cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            }

            if (!id.matches("S\\d{3}")) {
                JOptionPane.showMessageDialog(null, "ID is not a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!name.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(null, "Name is not valid", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!contact.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null, "Contact is not valid", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!validEntries(id)) {
                JOptionPane.showMessageDialog(null, "ID already exists", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Connection conn = DriverManager.getConnection(JDBCurl, JDBCusername, JDBCpassword);
                PreparedStatement ps = conn.prepareStatement("insert into staffs values(?,?,?)");
                ps.setString(1, id);
                ps.setString(2, name);
                ps.setString(3, contact);
                int result = ps.executeUpdate();

                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Staff Added", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Staff Not Added", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource() == back) {
            dispose();
            new DashBoard(USERNAME);
        }
        else if (e.getSource() == home) {
            dispose();
            new LoginPage();
        }
        else if (e.getSource() == clear) {
            ID.setText("");
            Name.setText("");
            Contact.setText("");
        }
    }

    private boolean validEntries(String id) {
        try {
            Connection conn = DriverManager.getConnection(JDBCurl, JDBCusername, JDBCpassword);
            Statement st = conn.createStatement();
            String query = "select * from staffs";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String StaffID = rs.getString("staffID");

                if (StaffID.equals(id)) {
                    return false;
                }
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

}
