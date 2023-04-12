package fileConversion;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
    	String inputFile = "C:\\Users\\User\\Desktop\\Coding Aids\\input.txt";
    	String outputFile = "output.txt";
    	
        //Read the input text file line by line using a FileReader or a BufferedReader
    	try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line;
			while((line = br.readLine()) != null)
				System.out.println(line);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
