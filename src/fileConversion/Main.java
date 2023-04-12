package fileConversion;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\User\\Desktop\\Coding Aids\\input.txt";
        String outputFile = "new_output.txt"; // Update to use .csv file extension

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bwr = new BufferedWriter(new FileWriter(outputFile))) { // Move BufferedWriter outside the loop

            String line;
            Map<String, String> keyValuePairs = new HashMap<>(); // Move map outside the loop
            while ((line = br.readLine()) != null) {
                String inputLine = line;
                String[] keyValue = inputLine.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    keyValuePairs.put(key, value);
                }
            }

            bwr.write("Key,Value");
            bwr.newLine();
            for (Map.Entry<String, String> entry : keyValuePairs.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                String csvRow = key + "," + value;
                bwr.write(csvRow);
                bwr.newLine();
            }

            System.out.println("Successfully wrote key-value pairs to CSV file: " + outputFile);

        } catch (IOException e) {
            System.err.println("Error writing key-value pairs to CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

