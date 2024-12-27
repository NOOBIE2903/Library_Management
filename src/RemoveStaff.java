import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RemoveStaff extends JFrame implements ActionListener {
    String USERNAME;
    private static final String JDBCusername = "root";
    private static final String JDBCpassword = "abhi74151";
    private static final String JDBCurl = "jdbc:mysql://localhost:3306/library";

    JLabel staffID;
    JButton removeStaff;
    JTextField ID;

    JButton back;
    JButton home;

    RemoveStaff(String username) {
        this.USERNAME = username;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Remove Staff");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        staffID = new JLabel("Staff ID");

        removeStaff = new JButton("Remove Staff");
        removeStaff.setFocusable(false);
        removeStaff.addActionListener(this);

        ID = new JTextField(10);

        back = new JButton("Back");
        back.setFocusable(false);
        back.addActionListener(this);

        home = new JButton("Home");
        home.setFocusable(false);
        home.addActionListener(this);

        staffID.setBounds(50, 50, 100, 30);
        ID.setBounds(150, 50, 200, 30);
        removeStaff.setBounds(150, 100, 150, 40);
        back.setBounds(110, 200, 100, 40);
        home.setBounds(250, 200, 100, 40);

        add(staffID);
        add(removeStaff);
        add(ID);
        add(back);
        add(home);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeStaff) {
            String id = ID.getText();

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a staff ID", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!id.matches("S\\d{3}")) {
                JOptionPane.showMessageDialog(null, "Please enter staff ID of the form Sxxx", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!validID(id)) {
                JOptionPane.showMessageDialog(null, "ID is not present.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Connection conn = DriverManager.getConnection(JDBCurl, JDBCusername, JDBCpassword);
                Statement stm = conn.createStatement();
                String query = "delete from staffs where staffID = '" + id + "'";
                int resultSet = stm.executeUpdate(query);
                if (resultSet > 0) {
                    dispose();
                    JOptionPane.showMessageDialog(null, "Staff deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new DashBoard(USERNAME);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Staff could not be deleted", "Error", JOptionPane.ERROR_MESSAGE);
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
    }

    private boolean validID (String id) {
        try {
            Connection conn = DriverManager.getConnection(JDBCurl, JDBCusername, JDBCpassword);
            Statement stm = conn.createStatement();
            String query = "select * from staffs";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                if (rs.getString("staffID").equals(id)) {
                    return true;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
