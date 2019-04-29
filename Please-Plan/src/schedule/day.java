package schedule;

import java.util.*;

public class day {

	private Date date;
	private scheduleSpot[] spotList = new scheduleSpot[10];
	
	public day(Date date){ // Create an empty day, were each spot only knows the clock.
		this.date = date;
		for(int i = 7; i <= 16; i++) {
			spotList[i-7] = new scheduleSpot(i);
		}
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public scheduleSpot getSpot(int time) {
		int index = -1;
		for(int i = 0; i < spotList.length; i++) {
			if(spotList[i].getTime() == time) {
				index = i;
				i = 10;
			}
		}
		return spotList[index];
	}
	
	public void printSpots() {
		for(int i = 0; i < spotList.length; i++) {
			System.out.println(spotList[i].toString());
		}
	}
}