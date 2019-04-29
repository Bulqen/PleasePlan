package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.DBConnect;

public class CEO extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JTextField userNameText, firstNameText, lastNameText, passwordText, userTypeText, removeName;

public CEO()
{
	mainFrame = new JFrame("CEO View");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainPanel = new JPanel();
    
    JLabel userNameLabel = new JLabel("Username");
    JLabel firstNameLabel = new JLabel("First name");
    JLabel LastNameLabel = new JLabel("Last name");
    JLabel passwordLabel = new JLabel("Password");
    JLabel userTypeLabel = new JLabel("Usertype");
    
    userNameText = new JTextField(20);
    firstNameText = new JTextField(20);
    lastNameText = new JTextField(20);
    passwordText = new JTextField(20);
    userTypeText = new JTextField(20);
    
    JButton addUser = new JButton("Add user");
    addUser.addActionListener(this);
    JButton logoutButton = new JButton("Logout");
    logoutButton.addActionListener(this);
    JButton removeUser = new JButton("Remove a user");
    removeUser.addActionListener(this);
    //addUser.addActionListener(this);
    
    mainPanel.add(userNameLabel);
    mainPanel.add(userNameText);
    mainPanel.add(firstNameLabel);
    mainPanel.add(firstNameText);
    mainPanel.add(LastNameLabel);
    mainPanel.add(lastNameText);
    mainPanel.add(passwordLabel);
    mainPanel.add(passwordText);
    mainPanel.add(userTypeLabel);
    mainPanel.add(userTypeText);
    mainPanel.add(addUser);
    mainPanel.add(logoutButton);
    mainPanel.add(removeUser);
    
    mainFrame.getContentPane().add(BorderLayout.CENTER,mainPanel);

    mainFrame.setSize(300,500);
    mainFrame.setVisible(true);
       
}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String buttonText = e.getActionCommand();
		DBConnect writeUser = new DBConnect();

		if (buttonText.equalsIgnoreCase("Add user")) {
			System.out.println("test");
			String userName = userNameText.getText();
			String firstName = firstNameText.getText();
			String lastName = lastNameText.getText();
			String password = passwordText.getText();
			String userType = userTypeText.getText();

			user temp = new user(-1, userName, firstName, lastName, password, userType);

			try {
				writeUser.addUser(temp);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		else if (buttonText.equals("Logout")) {
			mainFrame.setVisible(false);
			LoginView login = new LoginView();

		}

		else if (buttonText.equals("Remove a user")) {

			String userNameRemove = JOptionPane.showInputDialog("Enter the name to remove");
			System.out.println(userNameRemove);

			try {
				writeUser.removeFromUsers(userNameRemove);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}