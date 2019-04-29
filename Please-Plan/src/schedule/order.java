package schedule;

import java.util.Date;

public class order {
	private int orderNumber;
	private String expireDate;
	private String customer;
	private int numOfUnits;
	private String product;
	
	 // The year start counting on 1900 and jan = 00 in month.
	
	
	public order(int orderNumber) { // The least information an order can have is the ordernumber. If deleted we can make the order info = null
		this.orderNumber = orderNumber;
	}

	public order(int orderNumber, String expireDate, String customer, int numOfUnits, String product) {
		this.orderNumber = orderNumber;
		this.numOfUnits = numOfUnits;
		this.customer = customer;
		this.product = product;
		this.expireDate = expireDate; // Has to enter the (year, month, date) in the String ExpDate in the constructor
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getNumOfUnits() {
		return numOfUnits;
	}

	public void setNumOfUnits(int numOfUnits) {
		this.numOfUnits = numOfUnits;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", numOfUnits=" + numOfUnits + ", product=" + product
				+ ", expireDate=" + expireDate + "]";
	}
	
	
}
