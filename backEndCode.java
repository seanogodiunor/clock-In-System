package clockIn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class backEndCode {
	
	// MAIN
	public static void main(String[] args) {
		
		System.out.println(time());

		System.out.println(date());
		
	}
	
	
	// RETURN HOUR
	public static int timeHour() {

	    LocalTime time = LocalTime.now();
		return time.getHour();
	}
	
	
	// RETURN MINS
	public static int timeMins() {

	    LocalTime time = LocalTime.now();
		return time.getMinute();
	}
	
	

	
	
	// RETURN TIME
	public static String time() {

	    LocalTime time = LocalTime.now();
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
		String formattedTime = time.format(timeFormatter);
		
		return formattedTime;
	    
	}
	
	
	
	// RETURN DATE
	public static String date() {

		LocalDate date = LocalDate.now();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy");
		String formattedDate = date.format(dateFormatter);
		
		return formattedDate;
	}
	
	
	// RETURN SECOUNDS
//	public static int timeSec() {
//
//		    LocalTime time = LocalTime.now();
//			return time.getHour();
//	}
	
	
	
}

//}