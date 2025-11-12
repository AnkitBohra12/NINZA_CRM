package genericutilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public String getRequiredDate(int days) {
//		If we pass the days it will calculate the Date and return it
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String requiredDate = sim.format(cal.getTime());
		
		return requiredDate;
	}
	
	public int generateNineDigitNumber() {
//		to generate random number
		Random random = new Random();
		int randomNum = random.nextInt(100000000,999999999);  // RT --> Int // 1st and last 9 digit number		
		return randomNum;
	}
	
	public String getCurrentDateAndTime() {
//		to generate current date
		Date d = new Date();
		String date = d.toString().replace(" ", "_").replace(":", "_");
		return date;
	}
}
