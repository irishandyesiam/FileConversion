package fileConversion;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\User\\Desktop\\Coding Aids\\input.txt";
        String outputFile = "new_output.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bwr = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            List<Map<String, String>> keyValuePairsList = new ArrayList<>();
            Map<String, String> keyValuePairs = new HashMap<>();
            while ((line = br.readLine()) != null) {
                String inputLine = line;
                String[] keyValue = inputLine.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    keyValuePairs.put(key, value);
                } else {
                    // Assumes an empty line separates each set of key-value pairs
                    if (!keyValuePairs.isEmpty()) {
                        keyValuePairsList.add(keyValuePairs);
                        keyValuePairs = new HashMap<>();
                    }
                }
            }
            // Add the last set of key-value pairs after reaching the end of the file
            if (!keyValuePairs.isEmpty()) {
                keyValuePairsList.add(keyValuePairs);
            }

            // Write CSV header
            bwr.write("Name, Age, City, Gender");
            bwr.newLine();

            // Write CSV rows
            for (Map<String, String> kvp : keyValuePairsList) {
                String name = kvp.get("Name");
                String age = kvp.get("Age");
                String city = kvp.get("City");
                String gender = kvp.get("Gender");
                String csvRow = name + ", " + age + ", " + city + ", " + gender;
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







