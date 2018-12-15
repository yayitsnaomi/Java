package hw1;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Exercise3 {

	public static void main(String[] args) throws IOException {

		//Find and set path to file to read in data
		String pathname = "/Users/naomi/eclipse-workspace/hw1/src/employees.txt";
		File file = new File(pathname);

		//Initialize array lists 
		String[] split ;
		List<String> names = new ArrayList<>();
		List<String> birthday = new ArrayList<>();
		List<Integer> salary = new ArrayList<>();

		//Read in data row by row and store each data element in related list 
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = bufferedReader.readLine(); 
		while (line != null) {
			split = line.split(",");
			names.add(split[0]);
			birthday.add(split[1]);
			salary.add(Integer.valueOf(split[2]));
			line = bufferedReader.readLine();
		}

		bufferedReader.close();
		
		//Question 1: How many Employees exist in the file?
		countEmployees(names);

		//Question 2: Who has the highest salary?
		getMaxSalary(salary, names);

		//Question 3: What is the average salary?
		double avg = averageSalary(salary);

		//Question 4: How many employees make above the average? 
		countAboveAverage(salary, avg);

		//Question 5: What is the average age of employees?
		//Get today's date
		avgAge(birthday);


		

		//Question 6:
		//Define variables used to iterate through old salary loop 
		int temp_salary;
		String temp_name;

		//Bubble sort to order the salary and then reorder the names in the same index order
		for (int j = 0; j < salary.size()-1; j++){
			for (int i = 0; i < salary.size()-1; i++) {
				if (salary.get(i).compareTo(salary.get(i+1)) > 0){
					temp_salary = Integer.valueOf(salary.get(i));
					salary.set(i,salary.get(i+1) );
					salary.set(i+1, temp_salary);

					temp_name = names.get(i);
					names.set(i,names.get(i+1) );
					names.set(i+1, temp_name);

				}
			}
		}

		try {
			FileWriter writer = new FileWriter("employeesOrderedBySalary.txt");
			for (int z=0; z<names.size();z++) {
				String empName=names.get(z)+","+salary.get(z);
				writer.write(empName);
				writer.write("\r\n");
			}
			writer.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Question 6: Employee Names have been written to the file: employesBySalary.txt");

	}
	
	//Methods

	//Determines the number of employees ina  file by returning the size of the array 
	public static void countEmployees(List<String> names) {
		System.out.println("Question 1: The number of employees in the file is: " + names.size());
	}

	//Iterate through Salaries to find the max, then use the same index to look up the employee with that salary
	public static void getMaxSalary(List<Integer> salary, List<String> names) {
		Integer maxSalary = 0;
		for (int i = 0; i<salary.size(); i++) {
			if (maxSalary < salary.get(i)){
				maxSalary = salary.get(i);
			}
		}
		int index = salary.indexOf(maxSalary);
		System.out.println("Question 2: Employee with the Highest Salary is: "+ names.get(index));
	}

	//Calculate the average salary by looping through the salary array summing all elements & dividing by the size of the array
	public static double averageSalary(List<Integer> salary) {
		double total=0;

		for(int i = 0; i < salary.size(); i++)
		{
			total = total + Integer.valueOf(salary.get(i));
		}
		System.out.println("Question 3: The Average salary is: " + total / salary.size());
		return total / salary.size();
	}
	
	//Loop through the salary array and increment counter for each salary that is greater than the average calculated in the previous method
	public static void countAboveAverage(List<Integer> salary, double avg) {
		int counter=0;

		for(int i = 0; i < salary.size(); i++)
		{
			if(salary.get(i)>avg)
				counter ++;
		}
		System.out.println("Question 4: Count of employees that have > avg salary: " + counter);
	}

	//Calculate the average age by looking up today's date - the employee's birthday
	public static void avgAge(List<String> birthday) {
		SimpleDateFormat format1 =new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		ArrayList<Long> age = new ArrayList<Long>();

		for(int i=0; i < birthday.size();i++) {
			Date bday;
			try {
				bday = format1.parse(birthday.get(i));
				age.add(TimeUnit.MILLISECONDS.toMinutes(( (date.getTime() - bday.getTime() ))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		double avgAge = 0;
		for(int i =0; i < birthday.size();i++) {
			avgAge = avgAge  + ( age.get(i)/(60 * 24 * 365 ));
		}
		System.out.println("Question 5: Average of employees is : " + (avgAge/birthday.size()));
	}
}
