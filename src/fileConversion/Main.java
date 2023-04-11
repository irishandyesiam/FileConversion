package fileConversion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        String inputFile = "Members.txt";
        String outputFilePrefix = "_outputFile.csv";

        try {
            // Read input file
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;
            Map<String, FileWriter> outputFiles = new HashMap<>();
            Set<String> uniqueMembers = new HashSet<>();
            while ((line = br.readLine()) != null) {
                String id = line.substring(0, 12).trim();
                String lastName = line.substring(12, 37).trim();
                String firstName = line.substring(37, 62).trim();
                String address = line.substring(62, 92).trim();
                String city = line.substring(92, 112).trim();
                String state = line.substring(112, 116).trim();
                String zip = line.substring(116, 121).trim();
                String memberData = id + "," + firstName + "," + lastName + "," + address + "," + city + "," + zip;

                // Skip duplicate members
                if (uniqueMembers.contains(memberData)) {
                    continue;
                }
                uniqueMembers.add(memberData);

                // Create output file for each state if not exists
                if (!outputFiles.containsKey(state)) {
                    FileWriter fw = new FileWriter(state + outputFilePrefix);
                    outputFiles.put(state, fw);
                }

                // Write member data to respective output file
                FileWriter fw = outputFiles.get(state);
                fw.write(memberData + "\n");
            }

            // Close all output files
            for (FileWriter fw : outputFiles.values()) {
                fw.close();
            }

            System.out.println("Conversion completed successfully!");
        } catch (IOException e) {
            System.err.println("Error occurred during file I/O: " + e.getMessage());
        }
    }
}
