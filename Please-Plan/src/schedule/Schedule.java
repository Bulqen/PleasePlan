package schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Schedule {
	
	private day[] weekDay = new day[5];
	private Calendar c;
	
	public Schedule (Date date){
		
		for(int i = 0; i < weekDay.length; i++) {
			
			c = Calendar.getInstance();
			c.add(Calendar.DATE, i);
			
			if(c.get(Calendar.DAY_OF_MONTH)!= Calendar.SATURDAY || c.get(Calendar.DAY_OF_MONTH) != Calendar.SUNDAY) {
				date = c.getTime();
				weekDay[i] = new day(date);
			}
			else {
				c.add(Calendar.DATE, 1);
				i--;
			}
		}
	}
	
	public String getScheduleSpot (Date date, int time){ // En specifik scheduleSpot
		return "" + weekDay[0].getSpot(time);
	}
	
	public void printSchedule() {
		for(int i = 0; i < 5; i++) {
			System.out.println(weekDay[i].getDate());
			weekDay[i].printSpots();
		}
	}
	
	public void planOrder(order theOrder, int time, String date) {
		try {
			Date orderDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			weekDay[orderDate.getDay()].getSpot(time).planOrder(theOrder);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void planOperator(machineOperator theMO, int time, String date) {
		try {
			Date spotDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			weekDay[spotDate.getDay()].getSpot(time).planMachineOp(theMO);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
