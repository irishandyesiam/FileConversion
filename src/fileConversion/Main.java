package fileConversion;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\User\\Desktop\\Coding Aids\\input.txt";
        String outputFile = "new_output.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bwr = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            Map<String, String> keyValuePairs = new LinkedHashMap<>(); // Use LinkedHashMap instead of HashMap
            while ((line = br.readLine()) != null) {
                String inputLine = line;
                String[] keyValue = inputLine.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    keyValuePairs.put(key, value);
                }
            }

            // Write CSV header
            bwr.write("Name, Age, City, Gender");
            bwr.newLine();

            // Write CSV rows
            String name = keyValuePairs.get("Name");
            String age = keyValuePairs.get("Age");
            String city = keyValuePairs.get("City");
            String gender = keyValuePairs.get("Gender");
            String csvRow = name + ", " + age + ", " + city + ", " + gender;
            bwr.write(csvRow);
            bwr.newLine();

            System.out.println("Successfully wrote key-value pairs to CSV file: " + outputFile);

        } catch (IOException e) {
            System.err.println("Error writing key-value pairs to CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}





