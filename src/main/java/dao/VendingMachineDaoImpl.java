package dao;

import dto.Drink;
import dto.Item;
import dto.Snack;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineDaoImpl implements VendingMachineDao {

    public static int index = 1;
    public static final String DELIMITER = "::";
    public static final String FILE = "src/main/resources/Inventory.txt";
    private Map<Item, Integer> inventory = new HashMap<>();
    private Map<Integer, Item> products = new HashMap<>();
    private BigDecimal money;

    public VendingMachineDaoImpl() {
        populate();
    }

    @Override
    public Item removeItem(Item item) {
        if (inventory.containsKey(item)) {
            inventory.replace(item, inventory.get(item) - 1);
            return item;
        }
        return null;
    }

    @Override
    public Item addItem(Item item) {
        if (inventory.containsKey(item)) {
            inventory.replace(item, inventory.get(item) + 1);
        } else {
            products.put(index++,item);
            inventory.put(item, 1);
        }
        return item;
    }

    @Override
    public Map<Item, Integer> getAllItems() {
        return inventory;
    }

    @Override
    public Item getItem(int index) {
        if (products.containsKey(index)){
            return products.get(index);
        }
        return null;
    }

    @Override
    public BigDecimal getFunds() {
        return money;
    }

    @Override
    public BigDecimal addMoney(BigDecimal money) {
        this.money = money.setScale(2,RoundingMode.HALF_UP);
        return this.money;
    }

    @Override
    public BigDecimal change(BigDecimal money) {
        BigDecimal change = this.money.subtract(money);
        this.money = new BigDecimal("0");
        return change;
    }

    public Map<Integer,Item> getProducts(){
        return products;
    }

    private void populate() {
        this.products.put(index++, new Drink("Coca-cola", new BigDecimal("1.2")));
        this.products.put(index++, new Drink("Fanta", new BigDecimal("1.2")));
        this.products.put(index++, new Snack("Twix", new BigDecimal("1.2")));
        this.inventory.put(new Drink("Coca-cola", new BigDecimal("1.2")), 5);
        this.inventory.put(new Drink("Fanta", new BigDecimal("1.2")), 5);
        this.inventory.put(new Snack("Twix", new BigDecimal("1.2")), 5);
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
