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

    public VendingMachineDaoImpl() throws VendingMachineDaoPersistenceException {
        loadVendingMachine();
    }

    @Override
    public Item removeItem(Item item) throws VendingMachineDaoPersistenceException {
        if (inventory.containsKey(item)) {
            inventory.replace(item, inventory.get(item) - 1);
            writeInventory();
            return item;
        }
        return null;
    }

    @Override
    public Item addItem(Item item) {
        if (inventory.containsKey(item)) {
            inventory.replace(item, inventory.get(item) + 1);
        } else {
            products.put(index++, item);
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
        if (products.containsKey(index)) {
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
        this.money = money.setScale(2, RoundingMode.HALF_UP);
        return this.money;
    }

    @Override
    public BigDecimal change(BigDecimal money) {
        BigDecimal change = this.money.subtract(money);
        this.money = new BigDecimal("0");
        return change;
    }

    public Map<Integer, Item> getProducts() {
        return products;
    }


    private Map<Item, Integer> unmarshallItem(String itemtAsText) {
        // studentAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // DRINK::Coca-cola::1.2::5
        Map<Item, Integer> item = new HashMap<>();
        String[] itemTokens = itemtAsText.split(DELIMITER);

        String name = itemTokens[1];
        BigDecimal cost = new BigDecimal(itemTokens[2]);
        Integer amount = Integer.parseInt(itemTokens[3]);

        if (itemTokens[0].equals("DRINK")) {
            item.put(new Drink(name, cost), amount);
        } else {
            item.put(new Snack(name, cost), amount);
        }
        return item;
    }

    private void loadVendingMachine() throws VendingMachineDaoPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineDaoPersistenceException(
                    "-_- Could not load inventory data into memory.", e);
        }
        String currentLine;
        Map<Item, Integer> currentItem;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);

            inventory.putAll(currentItem);
        }

        inventory.forEach((key,value) -> products.put(index++, key));
        scanner.close();
    }

    private String marshallItem(Item aItem) {
        String itemAsText = aItem.toString() + DELIMITER;
        itemAsText += inventory.get(aItem);
        return itemAsText;
    }

    private void writeInventory() throws VendingMachineDaoPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(FILE));
        } catch (IOException e) {
            throw new VendingMachineDaoPersistenceException(
                    "Could not save inventory data.", e);
        }

        this.getAllItems().forEach((key, value) -> {
            String itemAsText = marshallItem(key);
            out.println(itemAsText);
            out.flush();
        });

//        List<Item> itemList = (List<Item>) this.getAllItems().keySet();
//        for (Student currentStudent : studentList) {
//            itemAsText = marshallStudent(currentStudent);
//            out.println(itemAsText);
//            out.flush();
//        }

        out.close();
    }

}
