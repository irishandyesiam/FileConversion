package fileConversion;
import java.io.*;
import java.util.*;

// Class to represent a member
class Member {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;

    public Member(String id, String firstName, String lastName, String address, String city, String state, String zip) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    // Getters and Setters
    // ...

    // toString method to convert member object to CSV string
    @Override
    public String toString() {
        return id + "," + firstName + "," + lastName + "," + address + "," + city + "," + zip;
    }

	public String getState() {
		return state;
	}
}

// Interface to export members to a CSV file
interface MemberExporter {
    void exportMembers(List<Member> members, String fileName) throws IOException;
}

// Interface to import members from a fixed-column format file
interface MemberImporter {
    List<Member> importMembers(String fileName) throws IOException;
}

// Implementation of MemberExporter interface
class MemberExporterImpl implements MemberExporter {
    @Override
    public void exportMembers(List<Member> members, String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Member member : members) {
                writer.println(member.toString());
            }
        }
    }
}

// Implementation of MemberImporter interface
class MemberImporterImpl implements MemberImporter {
    @Override
    public List<Member> importMembers(String fileName) throws IOException {
        List<Member> members = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String id = line.substring(0, 12).trim();
                String lastName = line.substring(12, 37).trim();
                String firstName = line.substring(37, 62).trim();
                String address = line.substring(62, 92).trim();
                String city = line.substring(92, 112).trim();
                String state = line.substring(112, 116).trim();
                String zip = line.substring(116).trim();
                Member member = new Member(id, firstName, lastName, address, city, state, zip);
                members.add(member);
            }
        }
        return members;
    }
}

// Main class to perform the conversion
public class Main {
    public static void main(String[] args) throws IOException {
        MemberImporter memberImporter = new MemberImporterImpl();
        List<Member> members = memberImporter.importMembers("Members.txt");
        Map<String, List<Member>> membersByState = new HashMap<>();
        for (Member member : members) {
            String state = member.getState();
            if (!membersByState.containsKey(state)) {
                membersByState.put(state, new ArrayList<>());
            }
            membersByState.get(state).add(member);
        }
        MemberExporter memberExporter = new MemberExporterImpl();
        for (String state : membersByState.keySet()) {
            String fileName = state + "_outputFile.csv";
            memberExporter.exportMembers(membersByState.get(state), fileName);
        }
    }
}
