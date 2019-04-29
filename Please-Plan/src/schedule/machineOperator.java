package schedule;

public class machineOperator {
	
	private int userID;
	private String name;
	
	public machineOperator(int userID) {
		this.userID = userID;
	}
	public machineOperator(int userID, String name) {
		this.userID = userID;
		this.name = name;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.getName() + " " + this.getUserID();
	}
	
	
	
}
