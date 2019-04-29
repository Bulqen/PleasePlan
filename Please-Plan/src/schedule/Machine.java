package schedule;

public class Machine {
	
	private int machineID;
	private String machineName;
	private int productID;
	
	public Machine (int machineID, String machineName, int productID)
	{
		this.machineID = machineID;
		this.machineName = machineName;
		this.productID = productID;
	}
	
	public int getMachineID() {
		return this.machineID;
	}
	public void getMachineID(int machineID) {
		this.machineID = machineID;
	}
	public String getMachineName() {
		return this.machineName;
	}

}
