package appointment;


public class oneTime extends Appointment  {

	int year;
	int month;
	int day;
	
	// Constructor public 
	public oneTime( String aptDescription, int year, int month, int day) {   
		super(aptDescription);
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	@Override
	//toString 
	public String toString() {
		return " "+aptDescription + " " + "oneTime apt on: " +day +"/"+ month +"/"+ year  ; 
	}
	
	@Override
	//Onetime: The exact date (month/day/year)
	public boolean occursOn(int year, int month, int day)
	{
		return (year == this.year) && (month == this.month) && (day == this.day);
	}

}