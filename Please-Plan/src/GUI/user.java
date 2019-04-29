package GUI;

public class user {
	private int ID;
	private String userName;
	private String Password;
	private String firstName;
	private String lastName;
	private String userType;
	public user(){
	
	}
	public user(int ID, String userName, String Password, String firstName, String lastName, String userType){
		this.setID(ID);
		this.setUserName(userName);
		this.setPassword(Password);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUserType(userType);
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
