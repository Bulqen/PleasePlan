package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import database.DBConnect;
import schedule.Machine;
import schedule.Schedule;
import schedule.order;
import schedule.machineOperator;

public class productionView extends JFrame implements ActionListener {
	// Vad som behövs globalt för productionView
	private JTextField orderID;
	private JTextField scheduleSpotText;
	private JTextField customer;
	private JTextField duedate;
	private JTextField price;
	private JFrame mainFrame;
	private Schedule sch;
	private Calendar c = Calendar.getInstance();
	private Date date = new Date();
	private DBConnect dbc = new DBConnect();
	private ArrayList<order> orderList;
	private ArrayList<Machine> machineList;
	private ArrayList<machineOperator> machineOperatorList;
	private JComboBox<String> orderIDBox;
	private JComboBox<String> machineBox;
	private JComboBox<String> machineOperatorBox;
	private String today = "";
	private int userID = -1;
	private int orderid = -1;
	private int machineID = -1;
	private int time = -1;

	public productionView() {
		try {
			orderList = dbc.readOrder();
			machineList = dbc.readMachine();
			machineOperatorList = dbc.readMachineOperator();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] orderid = new String[orderList.size()];
		String[] machineid = new String[machineList.size()];
		String[] machineOperator = new String[machineOperatorList.size()];

		for (int i = 0; i < orderList.size(); i++) {
			orderid[i] = String.valueOf(orderList.get(i).getOrderNumber());
		}
		for (int i = 0; i < machineList.size(); i++) {
			machineid[i] = machineList.get(i).getMachineName();
		}
		for (int i = 0; i < machineOperatorList.size(); i++) {
			machineOperator[i] = machineOperatorList.get(i).getName();
		}

		orderIDBox = new JComboBox<String>(orderid);
		machineBox = new JComboBox<String>(machineid);
		machineOperatorBox = new JComboBox<String>(machineOperator);
		orderIDBox.setSelectedIndex(0);
		orderIDBox.addActionListener(this);
		machineBox.setSelectedIndex(0);
		machineBox.addActionListener(this);
		machineOperatorBox.setSelectedIndex(0);
		machineOperatorBox.addActionListener(this);

		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(this);

		sch = new Schedule(date);
		c.setTime(date);

		mainFrame = new JFrame("scheduleView");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel1 = new JPanel();

		JLabel placeHolder = new JLabel("OrderID");

		JLabel productIdLabel = new JLabel("ScheduleSpot");
		JLabel machineOperatorLabel = new JLabel("MachineOperator");
		JLabel machineLabel = new JLabel("Machine");
		JLabel duedateLabel = new JLabel("Duedate");
		JLabel emptyLabel = new JLabel(" ");

		JButton addOrderLabel = new JButton("Add order to schedule");
		addOrderLabel.addActionListener(this);

		orderID = new JTextField(20);
		scheduleSpotText = new JTextField(20);
		duedate = new JTextField(20);
		price = new JTextField(20);
		
		scheduleSpotText.setEditable(false);
		duedate.setEditable(false);

		mainPanel.setLayout(new GridLayout(7, 2));

		mainPanel.add(placeHolder); // Håller plats till dropdown lösning
		mainPanel.add(orderIDBox);
		mainPanel.add(productIdLabel);
		mainPanel.add(scheduleSpotText);
		mainPanel.add(machineOperatorLabel);
		mainPanel.add(machineOperatorBox);
		mainPanel.add(machineLabel);
		mainPanel.add(machineBox);
		mainPanel.add(duedateLabel);
		mainPanel.add(duedate);

		mainPanel.add(emptyLabel); // Tom ruta för att få det rätt i rutnätet med addOrder
		mainPanel.add(addOrderLabel);
		mainPanel.add(logoutButton);

		mainPanel1.setLayout(new GridLayout(11, 5));

		mainPanel1.add(new JLabel(""));
		for (int i = 0; i < 5; i++) {
			c.add(Calendar.DATE, i);
			date = c.getTime();
			int day = (Calendar.DAY_OF_WEEK + i) % 7;
			mainPanel1.add(new JLabel("" + printDay(day)));
		}

		Font hideStyle = new Font("Arial", Font.PLAIN, 1); // Skapar en jätteliten font för knapparna så deras label ej
															// syns
		JLabel[] timeStamps = new JLabel[9];
		JButton[][] scheduleButtons = new JButton[9][5];
		for (int i = 0; i < 9; i++) // Skapar rutnätet för schemat
		{
			int clock = 7;
			timeStamps[i] = new JLabel("" + (clock + i) + ":00", SwingConstants.RIGHT);
			mainPanel1.add(timeStamps[i]);
			for (int j = 0; j < 5; j++) {
				c.add(Calendar.DATE, j);
				date = c.getTime();
				scheduleButtons[i][j] = new JButton();
				scheduleButtons[i][j].setText(i + "," + j);
				scheduleButtons[i][j].addActionListener(this); // Alla knappar finns med i action listener
				scheduleButtons[i][j].setFont(hideStyle); // Applicerar fonten till knappen
				scheduleButtons[i][j].setForeground(Color.white);// Vit färg för extra gömdhet
				mainPanel1.add(scheduleButtons[i][j]);
			}
		}
		JPanel titlePanel = new JPanel();
		titlePanel.add(new JLabel("Production Manager"));

		mainFrame.getContentPane().add(BorderLayout.NORTH, titlePanel);
		mainFrame.getContentPane().add(BorderLayout.WEST, mainPanel);
		mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel1);
		mainFrame.setSize(1000, 300);
		mainFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonText = e.getActionCommand();

		c = Calendar.getInstance();
		date = new Date();
		c.setTime(date);

		if (e.getSource() instanceof JButton) {
			if (buttonText == "Add order to schedule") {
				try {
					dbc.addOrderToSch(orderid, userID, machineID, today, time);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (buttonText.equals("Logout")) {
				mainFrame.setVisible(false);
				LoginView login = new LoginView();

			} else {
				String coordinates[] = buttonText.split(",");
				int x = Integer.parseInt(coordinates[0]);
				int y = Integer.parseInt(coordinates[1]);

				for (int i = 0; i < 9; i++) {
					c = Calendar.getInstance();
					c.setTime(date);
					for (int j = 0; j < 5; j++) {
						if (x == i && y == j) {
							c.add(Calendar.DATE, j);
							time = 7 + i;
							System.out.println("kl." + time + " " + printDay((Calendar.DAY_OF_WEEK + j) % 7) + " ");
							scheduleSpotText.setText("kl." + time + ":00 " + printDay((Calendar.DAY_OF_WEEK + j) % 7) + " ");

							today = String.valueOf(c.getTime());
							break;
						}
					}
				}
			}
		} else {
			orderid = Integer.parseInt(orderIDBox.getSelectedItem().toString());

			for (int i = 0; i < orderList.size(); i++) {
				if (orderid == orderList.get(i).getOrderNumber()) {
					duedate.setText(orderList.get(i).getExpireDate());
				}
			}
			String operatorName = String.valueOf(machineOperatorBox.getSelectedItem());

			for (int i = 0; i < machineOperatorList.size(); i++) {
				if (operatorName == machineOperatorList.get(i).getName()) {
					userID = machineOperatorList.get(i).getUserID();
				}
			}

			String machine = String.valueOf(machineBox.getSelectedItem());

			for (int i = 0; i < machineList.size(); i++) {
				if (machine == machineList.get(i).getMachineName()) {
					machineID = machineList.get(i).getMachineID();
				}
			}

		}

	}

	public String printDay(int day) { // Sunday = 1, Monday = 2...
		String theDay = "";
		if (day == 0)
			theDay = "Monday";
		else if (day == 1)
			theDay = "Tuesday";
		else if (day == 2)
			theDay = "Wednesday";
		else if (day == 3)
			theDay = "Thursday";
		else if (day == 4)
			theDay = "Friday";
		else if (day == 5)
			theDay = "Saturday";
		else if (day == 6)
			theDay = "Sunday";
		return theDay;
	}


}
