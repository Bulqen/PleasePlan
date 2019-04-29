package database;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import schedule.Machine;
import schedule.machineOperator;
import schedule.order;
import schedule.scheduleSpot;
import GUI.user;

public class DBConnect {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement ps = null;
	private ResultSet resultSet = null;

	public DBConnect() {
		connect = null;
		statement = null;
		ps = null;
		resultSet = null;
	}

	public ArrayList<scheduleSpot> readScheduleSpot() throws Exception {
		try {
			ArrayList<scheduleSpot> list = new ArrayList<scheduleSpot>();

			String DB_URL = "jdbc:mysql://blu-ray.student.bth.se/camc15?user=camc15&password=XLwQQUwnDYpf";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(DB_URL);
			statement = connect.createStatement();
			System.out.println("database connection established -> READ");
			resultSet = statement.executeQuery("SELECT * FROM ScheduleSpot");
			while (resultSet.next()) {
				{
					int SpotID = resultSet.getInt("SpotID");
					int UserID = resultSet.getInt("UserID");
					int MachineID = resultSet.getInt("MachineID");
					int OrderID = resultSet.getInt("OrderID");
					scheduleSpot temp = new scheduleSpot(SpotID, UserID, MachineID, OrderID);
					list.add(temp);
				}
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public ArrayList<machineOperator> readMachineOperator() throws Exception {
		try {
			ArrayList<machineOperator> list = new ArrayList<machineOperator>();

			String DB_URL = "jdbc:mysql://blu-ray.student.bth.se/camc15?user=camc15&password=XLwQQUwnDYpf";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(DB_URL);
			statement = connect.createStatement();
			System.out.println("database connection established -> READ");
			resultSet = statement.executeQuery("SELECT * FROM machineOperatorUsers");
			while (resultSet.next()) {
				{
					int operatorID = resultSet.getInt("userID");
					String firstName = resultSet.getString("FirstName");
					String lastName = resultSet.getString("LastName");
					String operatorName = concat(firstName, lastName);
					machineOperator temp = new machineOperator(operatorID, operatorName);
					list.add(temp);
				}
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public ArrayList<user> readUser() throws Exception {
		try {
			ArrayList<user> list = new ArrayList<user>();

			String DB_URL = "jdbc:mysql://blu-ray.student.bth.se/camc15?user=camc15&password=XLwQQUwnDYpf";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(DB_URL);
			statement = connect.createStatement();
			System.out.println("database connection established -> READ");
			resultSet = statement.executeQuery("SELECT * FROM User");
			while (resultSet.next()) {
				{
					int UserID = resultSet.getInt("UserId");
					String userName = resultSet.getString("userName");
					String Password = resultSet.getString("Password");
					String firstName = resultSet.getString("firstName");
					String lastName = resultSet.getString("lastName");
					String userType = resultSet.getString("userType");
					user temp = new user(UserID, userName, Password, firstName, lastName, userType);
					list.add(temp);
				}
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public ArrayList<order> readOrder() throws Exception {
		try {
			ArrayList<order> list = new ArrayList<order>();

			String DB_URL = "jdbc:mysql://blu-ray.student.bth.se/camc15?user=camc15&password=XLwQQUwnDYpf";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(DB_URL);
			statement = connect.createStatement();
			System.out.println("database connection established -> READ");
			resultSet = statement.executeQuery("SELECT * FROM orders");
			while (resultSet.next()) {
				{
					int orderNumber = resultSet.getInt("orderNumber");
					String expireDate = resultSet.getString("expireDate");
					String customer = resultSet.getString("customer");
					int numOfUnits = resultSet.getInt("numOfUnits");
					String product = resultSet.getString("product");
					order temp = new order(orderNumber, expireDate, customer, numOfUnits, product);
					list.add(temp);
				}
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public ArrayList<Machine> readMachine() throws Exception {
		try {
			ArrayList<Machine> list = new ArrayList<Machine>();

			String DB_URL = "jdbc:mysql://blu-ray.student.bth.se/camc15?user=camc15&password=XLwQQUwnDYpf";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(DB_URL);
			statement = connect.createStatement();
			System.out.println("database connection established -> READ");
			resultSet = statement.executeQuery("SELECT * FROM machine");
			while (resultSet.next()) {
				{
					int machineID = resultSet.getInt("machineID");
					String machineName = resultSet.getString("machineName");
					int productID = resultSet.getInt("productID");
					Machine temp = new Machine(machineID, machineName, productID);
					list.add(temp);
				}
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public void addOrder(order order) throws ClassNotFoundException {
		try {

			String DB_URL = "jdbc:mysql://blu-ray.student.bth.se/camc15?user=camc15&password=XLwQQUwnDYpf";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(DB_URL);
			statement = connect.createStatement();
			System.out.println("database connection established -> INSERT");

			String query = "INSERT INTO orders (expireDate, customer, numOfUnits, product) VALUES ( ?, ?, ?, ?)";

			PreparedStatement preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, order.getExpireDate());
			preparedStmt.setString(2, order.getCustomer());
			preparedStmt.setInt(3, order.getNumOfUnits());
			preparedStmt.setString(4, order.getProduct());

			preparedStmt.execute();

		} catch (Exception e) {

		}
	}

	public void addOrderToSch(int orderID, int userID, int machineID, String date, int time)
			throws ClassNotFoundException {
		try {

			String DB_URL = "jdbc:mysql://blu-ray.student.bth.se/camc15?user=camc15&password=XLwQQUwnDYpf";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(DB_URL);
			statement = connect.createStatement();
			System.out.println("database connection established -> INSERT");

			System.out.println(orderID + " " + userID + " " + machineID + " " + date + " " + time);

			String query = "INSERT INTO scheduleSpot (UserID, machineID, orderNumber, time, date) VALUES ( ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = connect.prepareStatement(query);
			preparedStmt.setInt(1, userID);
			preparedStmt.setInt(2, machineID);
			preparedStmt.setInt(3, orderID);
			preparedStmt.setInt(4, time);
			preparedStmt.setString(5, date);

			preparedStmt.execute();

		} catch (Exception e) {

		}

	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

	public void removeFromDB() throws ClassNotFoundException {
		try {

			String DB_URL = "jdbc:mysql://blu-ray.student.bth.se/camc15?user=camc15&password=XLwQQUwnDYpf";
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Setup the connection with the DB
			connect = DriverManager.getConnection(DB_URL);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			System.out.println("database connection established -> DELETE");

			String query = "DELETE FROM allTheOrders WHERE ordernummer1 = ?";

			PreparedStatement preparedStmt = connect.prepareStatement(query);
			preparedStmt.setInt(1, 6);

			preparedStmt.execute();

		} catch (Exception e) {

		}
	}

	public void addOrderTemplate(String customer, int numOfUnits, String product) throws ClassNotFoundException {
		try {

			String DB_URL = "jdbc:mysql://blu-ray.student.bth.se/camc15?user=camc15&password=XLwQQUwnDYpf";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(DB_URL);
			statement = connect.createStatement();
			System.out.println("database connection established -> INSERT");

			String query = "INSERT INTO orderTemplate ( customer, numOfUnits, product) VALUES ( ?, ?, ?)";

			PreparedStatement preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, customer);
			preparedStmt.setInt(2, numOfUnits);
			preparedStmt.setString(3, product);

			preparedStmt.execute();

		} catch (Exception e) {

		}

	}

	public ArrayList<order> readOrderTemplate() throws Exception {
		try {
			ArrayList<order> list = new ArrayList<order>();

			String DB_URL = "jdbc:mysql://blu-ray.student.bth.se/camc15?user=camc15&password=XLwQQUwnDYpf";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(DB_URL);
			statement = connect.createStatement();
			System.out.println("database connection established -> READ");
			resultSet = statement.executeQuery("SELECT * FROM orderTemplate");
			while (resultSet.next()) {
				{
					int orderTNumber = resultSet.getInt("templateId");
					String customer = resultSet.getString("customer");
					int numOfUnits = resultSet.getInt("numOfUnits");
					String product = resultSet.getString("product");
					order temp = new order(orderTNumber, "template", customer, numOfUnits, product);
					list.add(temp);
				}
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	public void removeFromUsers(String remove) throws ClassNotFoundException {
		try {

			String DB_URL = "jdbc:mysql://blu-ray.student.bth.se/camc15?user=camc15&password=XLwQQUwnDYpf";
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Setup the connection with the DB
			connect = DriverManager.getConnection(DB_URL);

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			System.out.println("database connection established -> DELETE");
			System.out.println(remove);

			String query = "DELETE FROM User WHERE UserName = ?";

			PreparedStatement preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, remove);

			preparedStmt.execute();

		} catch (Exception e) {

		}
	}
	public void addUser (user User) throws ClassNotFoundException
	{
		try
		{
			
			String DB_URL = "jdbc:mysql://blu-ray.student.bth.se/camc15?user=camc15&password=XLwQQUwnDYpf";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(DB_URL);
			statement = connect.createStatement();
			System.out.println("database connection established -> INSERT");
			
			String query = "INSERT INTO User ( UserName,  FirstName, LastName, Password, UserType) VALUES ( ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, User.getUserName());
			preparedStmt.setString(2, User.getFirstName());
			preparedStmt.setString(3, User.getLastName());
			preparedStmt.setString(4, User.getPassword());
			preparedStmt.setString(5, User.getUserType());
			
			preparedStmt.execute();

			
		} catch (Exception e)
		{
			
		}
		
	}


	public static String concat(String s1, String s2) {
		return s1 + " " + s2;
	}

}
