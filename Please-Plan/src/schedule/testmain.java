package schedule;

import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testmain {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		String test;
		Schedule sch = new Schedule(date);
		machineOperator MO = new machineOperator(1, "Kalle");
		
		//System.out.println(test + "\t" + testDate );
		
		order o = new order(10, "15/04", "IKEA", 10, "papper");
		
		sch.printSchedule();
		
		System.out.println(c.getTime());
		
		//sch.printScheduleDate();
		
		//sch.planOrder(o, 11, 12);
		
		//sch.printSchedule();
		
		System.out.print(17/2);
		
	}

}
