package schedule;

public class scheduleSpot {
	
	private int time;
	private machineOperator machineOp;
	private order myOrder;

	public scheduleSpot (int time){
		this.time = time;
		this.machineOp = null;
		this.myOrder = null;
	}
	
	public scheduleSpot (int day, int month, int orderID, int userID){
		this.machineOp = new machineOperator(userID);
		this.myOrder = new order(orderID);
	}

	public int getTime() {
		return this.time;
	}

	public void planMachineOp(machineOperator machineOp) {
		this.machineOp = machineOp;
	}
	
	public void planOrder(order theOrder) {
		this.myOrder = theOrder;
	}
	
	public String toString() {
		return "kl." + this.time + " Planned order: " + myOrder + " Planned MO: " + machineOp;
	}
	
}
