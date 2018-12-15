package appointment;

import java.time.LocalDate;

public class daily extends Appointment {
	//variables within appointment subclass daily 
	String startDate;
	String endDate;
	int endDay;
	int endMonth;
	int endYear;
	
	int startDay;
	int startMonth;
	int startYear;
	
	// Constructor public 
	public daily( String aptDescription, String startDate, String endDate) {  
		super(aptDescription);
		this.startDate = startDate;
		this.endDate = endDate;
		
		String[] start = startDate.split("/");
		this.startDay = Integer.parseInt(start[0]);
		this.startMonth = Integer.parseInt(start[1]);
		this.startYear = Integer.parseInt(start[2]);
		
		String[] end = endDate.split("/");
		this.endDay = Integer.parseInt(end[0]);
		this.endMonth = Integer.parseInt(end[1]);
		this.endYear = Integer.parseInt(end[2]);
		
	}
	
	//Daily date = (starting date and ending date)
	public String getMonthlyDate(int year, int month, int day) {
		return "Starting Date: " + year + "/" + month  + "/" + day
				+" \n Ending Date: ";
	}
	
	@Override
	//toString 
	public String toString() {
		return  " "+aptDescription + " daily atp from:  " + startDate + " to " + endDate; 
	}
	
	
	@Override
	public boolean occursOn(int year, int month, int day)
	{

		LocalDate dateToCheck = LocalDate.of(year, month, day);
		LocalDate startDate = LocalDate.of(this.startYear, this.startMonth, this.startDay);
		LocalDate endDate = LocalDate.of(this.endYear, this.endMonth, this.endDay);
		
		return (dateToCheck.isEqual(startDate) | dateToCheck.isEqual(endDate))| (dateToCheck.isAfter(startDate) && (dateToCheck.isBefore(endDate))) ;

	}

}
