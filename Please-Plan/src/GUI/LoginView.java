package GUI;

import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.DBConnect;

public class LoginView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame loggInFrame;
	private JPanel loggInPanel;
	private JTextField passwordText;
	private JTextField userNameText;
	private JButton button;
	private String password;
	private String username;

	LoginView() {
		

		loggInFrame = new JFrame("Hasse's LoggInPage");
		loggInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loggInPanel = new JPanel();
		loggInPanel.setLayout(new GridLayout(10,2));
		JLabel Username = new JLabel("Username:");
		Username.setFont(new Font("Arial", Font.PLAIN, 60));
		Username.setHorizontalAlignment(JTextField.CENTER);
		loggInPanel.add(Username);
		userNameText = new JTextField(20);
		userNameText.setFont(new Font("Arial", Font.PLAIN, 60));
		loggInPanel.add(userNameText);

		JLabel Password = new JLabel("Password:");
		Password.setFont(new Font("Arial", Font.PLAIN, 60));
		Password.setHorizontalAlignment(JTextField.CENTER);
		loggInPanel.add(Password);
		passwordText = new JTextField(20);
		passwordText.setFont(new Font("Arial", Font.PLAIN, 60));
		loggInPanel.add(passwordText);

		button = new JButton("Login");
		ActionHandler handler = new ActionHandler();

		button.setFont(new Font("Arial", Font.PLAIN, 60));
		button.setHorizontalTextPosition(JTextField.CENTER);
		button.addActionListener(handler);
		loggInPanel.add(button);
		loggInFrame.getContentPane().add(BorderLayout.CENTER, loggInPanel);
		loggInFrame.setSize(1400, 1400);
		loggInFrame.setVisible(true);
		
		loggInFrame.getRootPane().setDefaultButton(button);
		button.requestFocus();

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			setUsername(userNameText.getText());
			setPassword(passwordText.getText());
			DBConnect readUsers= new DBConnect();
			String userType="user";
			ArrayList<user> userList= new ArrayList<user>();
			try {
				userList= readUsers.readUser();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (int i=0;i<userList.size();i++){
				if (username.equals(userList.get(i).getUserName())&& password.equals(userList.get(i).getPassword())){
					userType= userList.get(i).getUserType();
					i=userList.size();
					loggInFrame.setVisible(false);
				}
			}
			if(userType.equals("CEO")){
				CEO CEOView= new CEO();
			}
			else if(userType.equals("salesMan")){
				SalesView salesView= new SalesView();
			}
			else if(userType.equals("productionManager")){
				productionView productionManView= new productionView();
			}
			else if(userType.equals("machineOperator")){
				MachineOperatorView machineView= new MachineOperatorView();
			}
			else {
				JOptionPane.showMessageDialog(null, "Wrong username and/or password try again","Invailid username/password",JOptionPane.ERROR_MESSAGE);
				passwordText.setText("");
				}
			
		}
	}
}
