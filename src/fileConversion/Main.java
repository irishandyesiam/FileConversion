package fileConversion;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) {
		//Make new BufferedWriter object, pass in file, needs to be wrapped in try/catch, 
		
		String[] names = {"John","Carl","Jerry"};
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
			writer.write("Writing to a file.");
			writer.write("\nHere is another line.");
			
			for (String name : names) {
				writer.write("\n" + name);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\eclipse-workspace\\FileConversion\\output.txt"));
			String line;
			while((line = reader.readLine()) != null)
				System.out.println(line);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
