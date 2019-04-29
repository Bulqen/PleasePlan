package GUI;

import javax.swing.*;

import database.DBConnect;
import schedule.order;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SalesView extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField QuantityT, CustomerT, DuedateT, ProductT;
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JComboBox loadTemplateBox;
	private ArrayList<order> templateList = null;
	SalesView() {
		mainFrame = new JFrame("SalesView");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		JLabel DuedateL = new JLabel("Expire date");
		JLabel CustomerL = new JLabel("Customer");
		JLabel QuantityL = new JLabel("Number of units");
		JLabel ProductL = new JLabel("Product");
		QuantityT = new JTextField(20);
		CustomerT = new JTextField(20);
		DuedateT = new JTextField(20);
		ProductT = new JTextField(20);
		JButton addOrder = new JButton("Add order");
		addOrder.addActionListener(this);
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(this);
		JButton createTemplateButton = new JButton("Create order template");
		createTemplateButton.addActionListener(this);
		DBConnect readTemplate = new DBConnect();

		
			
			try {
				templateList = readTemplate.readOrderTemplate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] templates = new String[templateList.size()];
			for (int i=0;i<templates.length;i++){
				templates[i]= String.valueOf(templateList.get(i).getOrderNumber());
			}
			loadTemplateBox = new JComboBox(templates);
			loadTemplateBox.addActionListener(this);
		

		

		mainPanel.add(CustomerL);
		mainPanel.add(CustomerT);
		mainPanel.add(DuedateL);
		mainPanel.add(DuedateT);
		mainPanel.add(ProductL);
		mainPanel.add(ProductT);
		mainPanel.add(QuantityL);
		mainPanel.add(QuantityT);
		mainPanel.add(addOrder);
		mainPanel.add(logoutButton);
		mainPanel.add(createTemplateButton);
		mainPanel.add(loadTemplateBox);

		mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);

		mainFrame.setSize(300, 500);
		mainFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String buttonText = e.getActionCommand();
		DBConnect DBC = new DBConnect();
		if (buttonText.equalsIgnoreCase("Add order")) {

			int orderNumber = -1;
			String expireDate = DuedateT.getText();
			String customer = CustomerT.getText();
			String units = QuantityT.getText();
			int numOfUnits = Integer.parseInt(units);
			String product = ProductT.getText();

			order temp = new order(orderNumber, expireDate, customer, numOfUnits, product);

			try {
				System.out.println(customer);
				DBC.addOrder(temp);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (buttonText.equals("Logout")) {
			mainFrame.setVisible(false);
			LoginView login = new LoginView();
		}

		else if (buttonText.equals("Create order template")) {

			String customer = CustomerT.getText();
			String units = QuantityT.getText();
			int numOfUnits = Integer.parseInt(units);
			String product = ProductT.getText();

			try {
				DBC.addOrderTemplate(customer, numOfUnits, product);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else{
			int boxNumber= Integer.parseInt(loadTemplateBox.getSelectedItem().toString());
			for (int i=0;i<templateList.size();i++){
				if (templateList.get(i).getOrderNumber()==boxNumber){
			CustomerT.setText(templateList.get(i).getCustomer());
			QuantityT.setText(String.valueOf(templateList.get(i).getNumOfUnits()));
			ProductT.setText(templateList.get(i).getProduct());
				}
			}
		}
	}
}



