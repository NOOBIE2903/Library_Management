import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditAdmin extends JFrame implements ActionListener {
    String USERNAME;

    JButton changePass;
    JButton changeName;
    JButton changeUserID;
    JButton changeContact;
    JButton deleteAdmin;

    JButton back;
    JButton home;

    EditAdmin(String username) {
        this.USERNAME = username;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Edit Admin");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(null);

        changeUserID = new JButton("Change User ID");
        changePass = new JButton("Change Password");
        changeName = new JButton("Change Name");
        changeContact = new JButton("Change Contact");
        deleteAdmin = new JButton("Delete Admin");

        back = new JButton("Back");
        home = new JButton("Home");

        changeUserID.setFocusable(false);
        changePass.setFocusable(false);
        changeName.setFocusable(false);
        changeContact.setFocusable(false);
        deleteAdmin.setFocusable(false);

        back.setFocusable(false);
        home.setFocusable(false);

        changeUserID.setBounds(50, 50, 150, 40);   // Change User ID button
        changePass.setBounds(50, 100, 150, 40);     // Change Password button
        changeName.setBounds(50, 150, 150, 40);     // Change Name button
        changeContact.setBounds(50, 200, 150, 40);  // Change Contact button
        deleteAdmin.setBounds(50, 250, 150, 40);    // Delete Admin button

        back.setBounds(50, 350, 100, 40);           // Back button
        home.setBounds(200, 350, 100, 40);          // Home button


        changeUserID.addActionListener(this);
        changePass.addActionListener(this);
        changeName.addActionListener(this);
        changeContact.addActionListener(this);
        deleteAdmin.addActionListener(this);

        back.addActionListener(this);
        home.addActionListener(this);

        add(changeUserID);
        add(changePass);
        add(changeName);
        add(changeContact);
        add(deleteAdmin);
        add(back);
        add(home);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeUserID) {
            dispose();
            new ChangeUserID(USERNAME);
        }
        if (e.getSource() == changePass) {
            dispose();
            new ChangePassword(USERNAME);
        }
        else if (e.getSource() == changeName) {
            dispose();
            new ChangeName(USERNAME);
        }
        else if (e.getSource() == changeContact) {
            dispose();
            new ChangeContact(USERNAME);
        }
        else if (e.getSource() == deleteAdmin) {
            dispose();
            new DeleteAdmin(USERNAME);
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
}
