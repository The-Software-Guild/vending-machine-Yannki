package dao;

import dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineDaoImpl implements VendingMachineDao {

    public static final String DELIMITER = "::";
    public static final String FILE = "src/main/resources/Inventory.txt";
    private HashMap<Item, Integer> inventory = new HashMap<>();
    private BigDecimal money = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);

    @Override
    public Item removeItem(Item item) {
        return null;
    }

    @Override
    public Item addItem(Item item) {
        return null;
    }

    @Override
    public Map<Item, BigDecimal> getAllItems() {
        return null;
    }

    @Override
    public Item getItem(Item item) {
        return null;
    }

    @Override
    public BigDecimal fund(BigDecimal money) {
        return null;
    }

    @Override
    public BigDecimal change(BigDecimal money) {
        return null;
    }


    private Item unmarshallStudent(String studentAsText) {
        // studentAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // 1234::Ada::Lovelace::Java-September1842

//        String[] studentTokens = studentAsText.split(DELIMITER);
//
//        String studentId = studentTokens[0];
//
//        Student studentFromFile = new Student(studentId);
//
//
//        studentFromFile.setFirstName(studentTokens[1]);
//
//        studentFromFile.setLastName(studentTokens[2]);
//
//        studentFromFile.setCohort(studentTokens[3]);
//
//        return studentFromFile;
        return null;
    }

    private void loadRoster() throws VendingMachineDaoPersistenceException {
//        Scanner scanner;
//
//        try {
//            scanner = new Scanner(
//                    new BufferedReader(
//                            new FileReader(ROSTER_FILE)));
//        } catch (FileNotFoundException e) {
//            throw new ClassRosterPersistenceException(
//                    "-_- Could not load roster data into memory.", e);
//        }
//        String currentLine;
//        Student currentStudent;
//
//        while (scanner.hasNextLine()) {
//            currentLine = scanner.nextLine();
//            currentStudent = unmarshallStudent(currentLine);
//
//            students.put(currentStudent.getStudentId(), currentStudent);
//        }
//        scanner.close();
    }

    private String marshallStudent(Item aStudent) {
//        String studentAsText = aStudent.getStudentId() + DELIMITER;
//
//
//        studentAsText += aStudent.getFirstName() + DELIMITER;
//
//        studentAsText += aStudent.getLastName() + DELIMITER;
//
//        studentAsText += aStudent.getCohort();

        return "";
    }

    private void writeRoster() throws VendingMachineDaoPersistenceException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to
        // handle any errors that occur.
//        PrintWriter out;
//
//        try {
//            out = new PrintWriter(new FileWriter(ROSTER_FILE));
//        } catch (IOException e) {
//            throw new ClassRosterPersistenceException(
//                    "Could not save student data.", e);
//        }
//
//        // Write out the Student objects to the roster file.
//        // NOTE TO THE APPRENTICES: We could just grab the student map,
//        // get the Collection of Students and iterate over them but we've
//        // already created a method that gets a List of Students so
//        // we'll reuse it.
//        String studentAsText;
//        List<Student> studentList = this.getAllStudents();
//        for (Student currentStudent : studentList) {
//            // turn a Student into a String
//            studentAsText = marshallStudent(currentStudent);
//            // write the Student object to the file
//            out.println(studentAsText);
//            // force PrintWriter to write line to the file
//            out.flush();
//        }
//        // Clean up
//        out.close();
    }

}
