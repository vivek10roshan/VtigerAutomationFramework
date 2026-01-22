package genericUtility;

import java.util.Date;
import java.util.Random;


/**
 * This class consist of methods related to Java
 */

public class JavaUtility {
	/**
	 * This method is used to generate Random number
	 * @return
	 */
	public int toGenerateRandomNumber() {
		Random r = new Random();
		int value = r.nextInt(1000);
		return value;
	}
	
	/**
	 * 
	 * @return
	 */
	public String toGenerateSystemDateAndTimeInFormat() {
		Date d = new Date();
		String date[] = d.toString().split(" ");
		String day = date[0];
		String month = date[1];
		String date1 = date[2];
		String time = date[3].replace(":", "-");
		String year = date[4];
		String finalDate = day+" "+month+" "+date1+" "+time+" "+year;
		System.out.println(finalDate);
		return finalDate;
		
	}
}
