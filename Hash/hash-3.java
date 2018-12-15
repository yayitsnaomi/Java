import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class hash {
	//hash 1 helper function - convert string to ASCII value
	public static int toAscii(String s){
		int i;
		char word[] = s.toCharArray();
		int sum = 0;
		int charVal;

		for (i=0; i < word.length; ++i) {
			charVal = (int) word[i] ;
			sum += charVal;
		} 
		return(sum);

	}

	//hash 1 function
	// Create a HashTable to store  - String values corresponding to integer keys 
	public static int hashASCII(ArrayList<String> nameList, Integer arrayLength) throws IOException {
		//create new hashed named arraylist with arraylist so that during collisions names can easily be added
		ArrayList<String> hashNames = new ArrayList<String>();
		String nameToHash ="";
		long hashValue;
		int index;
		String originalName;
		Integer collisionCount =0;

		//initialize array with specified length
		for(int i = 0; i<arrayLength; i++) {
			hashNames.add(i, "");
		}

		//Convert names to ASCII value and add into list
		for(int i = 0; i< nameList.size(); i++) {
			//get ascii value of name and take mod
			hashValue = toAscii(nameList.get(i));
			hashValue = hashValue % arrayLength;
			//Check that index
			index = (int) hashValue;

			if(hashNames.get(index)!="") {
				originalName = hashNames.get(index);
				nameToHash = originalName + " || " + nameList.get(i);
				collisionCount ++;
			}
			else {
				nameToHash = nameList.get(i);

			}
			hashNames.set(index, nameToHash);
		}

		// Printing the Hashtable 
		System.out.println(hashNames); 

		///write to file
		writeToFile(hashNames, "hash1.txt");
		System.out.println("Please open file: hash1.txt to see output written to file"+"\n");

		return collisionCount;
	}


	//hash 2 function
	// Create a HashTable to store  - String values to java hash code
	public static int hashJava(ArrayList<String> nameList, Integer arrayLength) throws IOException {
		//create new hashed named arraylist with arraylist so that during collisions names can easily be added
		ArrayList<String> hashNames = new ArrayList<String>();
		String nameToHash ="";
		int index;
		String originalName;
		Integer collisionCount =0;

		//initialize array with specified length
		for(int i = 0; i<arrayLength; i++) {
			hashNames.add(i, "");
		}

		//Convert names to java has value
		for(int i = 0; i< nameList.size(); i++) {
			//get java hash value as index
			index = nameList.get(i).hashCode() % arrayLength;
			index = Math.abs(index);
			//Check that index

			if(hashNames.get(index)!="") {
				originalName = hashNames.get(index);
				nameToHash = originalName + " || " + nameList.get(i);
				collisionCount ++;

			}
			else {
				nameToHash = nameList.get(i);

			}
			hashNames.set(index, nameToHash);
		}

		// Printing the Hashtable 
		System.out.println(hashNames); 

		///write to file
		writeToFile(hashNames, "hash2.txt");
		System.out.println("Please open file: hash2.txt to see output written to file"+"\n");

		return collisionCount;
	}

	//hash 3 function
	// Create a HashTable to store  - String values to custom hash code using random prime number generator
	public static int customhash(ArrayList<String> nameList, Integer arrayLength) throws IOException {
		//create new hashed named arraylist with arraylist so that during collisions names can easily be added
		ArrayList<String> hashNames = new ArrayList<String>();
		String nameToHash ="";
		int index;
		String originalName;
		Integer collisionCount =0;

		//random prime number generator for custom hash function
		int num = 0;
		Random rand = new Random(); // generate a random number
		num = rand.nextInt(1000) + 1;


		//initialize array with specified length
		for(int i = 0; i<arrayLength; i++) {
			hashNames.add(i, "");
		}

		//Convert names to java has value
		for(int i = 0; i< nameList.size(); i++) {
			while (!isPrime(num)) {          
				num = rand.nextInt(10000)+1;
			}
			index = i * num % arrayLength;

			if(hashNames.get(index)!="") {
				originalName = hashNames.get(index);
				nameToHash = nameList.get(i) + " || " + originalName;
				collisionCount++;
			}
			else {
				nameToHash = nameList.get(i);

			}
			hashNames.set(index, nameToHash);
			num = -1;

		}

		// Printing the Hashtable 
		System.out.println(hashNames); 

		///write to file
		writeToFile(hashNames, "hash3.txt");
		System.out.println("Please open file: hash3.txt to see output written to file"+"\n");

		return collisionCount;
	}


	public static void writeToFile(ArrayList<String> hashNames, String name) throws IOException {
		///write to file
		FileWriter writer = new FileWriter(name);

		for(int i =0; i<hashNames.size(); i++) {
			if(hashNames.get(i)=="") {
				writer.write(i + " ");
				writer.write("\r\n");
			}
			else {
				writer.write(i + " " + hashNames.get(i));
				writer.write("\r\n");
			}
		}
		writer.close();
	}

	public static void writeMetricFile(int hash1, int hash2, int hash3) throws IOException {
		///write to file
		FileWriter writer = new FileWriter("result.txt");
		writer.write("This is the result of 3 hash functions, measured by number of collisions \r\n");
		writer.write("ASCII Hash Function Collisions: " + hash1 +"\r\n");
		writer.write("Java Hash Function Collisions: "  + hash2 +"\r\n");
		writer.write("Custom Hash Function Collisions: " + hash3 +"\r\n");
		writer.close();
	}

	//prime number helper function for custom hash function
	private static boolean isPrime(int inputNum){
		if (inputNum <= 3 || inputNum % 2 == 0) 
			return inputNum == 2 || inputNum == 3; //this returns false if number is <=1 & true if number = 2 or 3
		int divisor = 3;
		while ((divisor <= Math.sqrt(inputNum)) && (inputNum % divisor != 0)) 
			divisor += 2; //iterates through all possible divisors
		return inputNum % divisor != 0; //returns true/false
	}

	public static void metric(int hash1, int hash2, int hash3) throws IOException {
		System.out.println("Metric: Number of collision per line in hashing function");
		System.out.println("     ASCII Collision Count: "+ hash1);
		System.out.println("     Java Collision Count: "+ hash2);
		System.out.println("     Custom Hash Collision Count: "+hash3);
		writeMetricFile(hash1, hash2, hash3);
		System.out.println("Please find results in result.txt file");
	}

	//parse input file helper function
	public static int parseInput(ArrayList<String> names) throws IOException {

		//Find and set path to file to read in data
		String pathname = "/Users/naomi/eclipse-workspace/Hash/src/input.txt";

		BufferedReader bufferedReader = new BufferedReader(new FileReader(pathname));
		String line = bufferedReader.readLine(); 
		//Initialize array lists 
		String[] split ;

		while (line != null) {
			split = line.split(",");
			names.add(split[0]);
			line = bufferedReader.readLine();
		}

		bufferedReader.close();
		return names.size();
	}

	/////////////////////////////////
	///main
	/////////////////////////////////

	public static void main(String[] args) throws IOException {
		//get user input for array size (100 or 200)
		Scanner in = new Scanner(System.in);
		int arraySize = -1;

		while( arraySize != 100 || arraySize !=200) {
			try {
				in = new Scanner(System.in);
				//Determine appointment type
				System.out.println("Please enter the size of the array (100 or 200)");
				arraySize = in.nextInt();
			}			
			catch(Exception e) {
				System.out.println("Please select 100 or 200");
				in.reset();
			}
			if(arraySize == 100 || arraySize ==200) {
				break;
			}
		}
		in.close();

		//Initialize array list
		ArrayList<String> names = new ArrayList<>();

		//Read in data row by row and store each data element in related list 
		parseInput(names);

		//Call hash functions
		//Metric to measure hash functions - count number of collisions
		int hash1=hashASCII(names, arraySize);
		int hash2=hashJava(names, arraySize);
		int hash3=customhash(names, arraySize);

		//Metric Output
		metric(hash1, hash2, hash3);
	}

}
