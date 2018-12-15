package appointment;

import java.time.LocalDate;

public class monthly extends Appointment {
	
	//variables within appointment subclass monthly 
	Integer day;
	String startMonthYear;
	String endMonthYear;
	int startMonth;
	int startYear;
	int endMonth;
	int endYear;
	
	// Constructor public 
	public monthly( String aptDescription, Integer day, String startMonthYear, String endMonthYear) {   
		super(aptDescription);
		this.day = day;
		this.startMonthYear = startMonthYear;
		this.endMonthYear = endMonthYear;
		
		String[] end = endMonthYear.split("/");
		this.endMonth = Integer.parseInt(end[0]);
		this.endYear = Integer.parseInt(end[1]);
		
		String[] start = startMonthYear.split("/");
		this.startMonth = Integer.parseInt(start[0]);
		this.startYear = Integer.parseInt(start[1]);
		
	}
	@Override
	//toString 
	public String toString() {
		return " "+aptDescription + " monthly atp from:  " + day+"/"+startMonthYear + " to " + day+"/"+endMonthYear; 
	}
	
	@Override
	public boolean occursOn(int year, int month, int day)
	{

		LocalDate dateToCheck = LocalDate.of(year, month, day);
		LocalDate startDate = LocalDate.of(this.startYear, this.startMonth, this.day);
		LocalDate endDate = LocalDate.of(this.endYear, this.endMonth, this.day);
		
		return (dateToCheck.isEqual(startDate) | dateToCheck.isEqual(endDate ) | (( dateToCheck.isAfter(startDate) &&  dateToCheck.isBefore(endDate)) && day == this.day));
	}
	
}
