package practice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetTheDataAfterSomeDays {

	public static void main(String[] args) {
		
//		Generate date after 30 days
		Date date = new Date();
		System.out.println(date);
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		String d = sim.format(date);
		System.out.println(d);
		Calendar cal = sim.getCalendar();    // it will return as calender
		cal.add(Calendar.DAY_OF_MONTH, 30);  // day of month we have to add thats y we have added
		String requiredDate = sim.format(cal.getTime());
		System.out.println(requiredDate);

	}

}
