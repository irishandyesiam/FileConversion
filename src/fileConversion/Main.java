package fileConversion;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
    	String inputFile = "C:\\Users\\User\\Desktop\\Coding Aids\\input.txt";
    	String outputFile = "output.txt";
    	
        //Step 1: Read the input text file line by line using a FileReader or a BufferedReader
    	try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line;
			while ((line = br.readLine()) != null) {
				// Step 2: Parse line to extract key-value pairs
				String inputLine = line; // Replace with the actual line read from the input file
				Map<String, String> keyValuePairs = new HashMap<>(); // Use a suitable data structure to store key-value pairs
				String[] keyValue = inputLine.split(":"); // Split line using colon as delimiter
				if (keyValue.length == 2) {
				    String key = keyValue[0].trim(); // Extract key and trim leading/trailing spaces
				    String value = keyValue[1].trim(); // Extract value and trim leading/trailing spaces
				    keyValuePairs.put(key, value); // Store key-value pair in the data structure
				}
			}	

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
