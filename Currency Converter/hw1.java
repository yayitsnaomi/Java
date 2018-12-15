package hw1;

import java.io.IOException;

/**
 * 
 */
/**
 * @author naomi nak133
 * 
 * Exercise 1 - Currency convertor
 * Write a java program that:
 * - Ask the user to type the price for one dollar in Japanese yen.
 * - The program will allow the user to convert from dollars to yen and vice versa.
 * - At any point of time the user can request to change the conversion rate.
 * - The program stops after a sentinel character entry.
 * Insure user entry validation at all time. No Crashes or unexpected results.
 *
 */

import java.util.Scanner;
public class hw1 {

	public static void main(String[] args) throws IOException {

		// define variables

		String us_dollar = "Dollars";
		String yen = "Yen";
		double rate = 0.0;
		int choice = -1;
		double startingValue = 0.0;
		int firstTime = 0;

		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to Currency Converter \n");


		while (choice!=0) {

			try {
				in = new Scanner(System.in);

				//If first time user needs to enter the exchange rate
				if(firstTime ==0) {
					rate = setExchangeRate(rate, in);
					firstTime ++;
				}

				// Interface -  Read in input 
				choice = displayMenu(choice, in);

				// US >> Yen
				if(choice == 1) { 
					startingValue = getStartValue(startingValue, in);
					System.out.println(startingValue * rate + " "+ yen);
				}
				// Yen >> US
				else if(choice ==2) { 
					startingValue = getStartValue(startingValue, in);
					System.out.println(startingValue/rate + " "+ us_dollar);
				}
				// Reset Exchange Rate
				else if (choice==3) {
					rate = setExchangeRate(rate, in);
				}
				// Exit
				else if (choice==0) {
					System.out.println("Thank you for using the currency converter! See you next time! Have a great day!  :)");
					break;
				}
				// Retry
				else {
					System.out.println("Please select a viable opion for the program");
				}
			}
			catch (Exception e) {
				System.out.println("Please select a viable opion for the program");
				in.reset();
			}
		}
	}

	//method to set and reset exchange rate
	public static double setExchangeRate(double rate, Scanner in) {
		System.out.println("Please enter the currency exchange rate: $1 US = how many Yen? : ");
		rate = in.nextDouble();
		if(rate<0) {
			System.out.println("Please enter a positive rate amount");
			rate = in.nextDouble();
		}
		return rate;
	}

	//method to display the console menu to user and get the next action
	public static int displayMenu(int choice, Scanner in) {
		System.out.println();
		System.out.println("Please select your option: \n 1 - Convert Dollars >> Yen \n 2 - Convert Yen >> Dollars \n 3 - Update Exchange Rate \n 0 - Exit \n");
		choice = in.nextInt();
		return choice;
	}

	//method to get the starting value from user for computation of the exchange rate value in another currency
	public static double getStartValue(double startingValue, Scanner in) {
		System.out.print("Please enter the starting amount: ");
		startingValue = in.nextDouble();
		while(startingValue<0) {
			System.out.println("Please enter a positive starting amount");
			startingValue = in.nextDouble();
		}
		return startingValue;
	}

}
