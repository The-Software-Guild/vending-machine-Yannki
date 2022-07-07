package dao;

import dto.Drink;
import dto.Item;
import dto.Snack;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineDaoImpl implements VendingMachineDao {

    public static int index = 1;
    public static final String DELIMITER = "::";
    public static final String FILE = "src/main/resources/Inventory.txt";
    private Map<Integer, Item> inventory = new HashMap<>();
    private Map<Integer, Item> products = new HashMap<Integer, Item>();
    private BigDecimal money;

    public VendingMachineDaoImpl() throws VendingMachineDaoPersistenceException {
        loadVendingMachine();
    }

    @Override
    public Item buyItem(Integer index) throws VendingMachineDaoPersistenceException {
        if (inventory.containsKey(index) && inventory.get(index).getQuantity() > 0) {
            inventory.get(index).decreaseQuantity();
            writeInventory();
            return inventory.get(index);
        }
        return null;
    }

    @Override
    public Item addItem(Item item) {
        if (inventory.containsValue(item)) {
            int index = 0;
            for (int i = 0; i < inventory.size(); i++){
                if (inventory.get(i).equals(item)) {
                    index = i;
                    break;
                }
            }
            inventory.replace(index,item);
        } else {
            inventory.put(index++, item);
        }
        return item;
    }

    @Override
    public Map<Integer, Item> getInventory() {
        return inventory;
    }

    @Override
    public Item getItem(Integer index) {
        if (inventory.containsKey(index)) {
            return inventory.get(index);
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


    private Map<Integer, Item> unmarshallItem(String itemtAsText) {
        // studentAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // DRINK::Coca-cola::1.2::5
        Map<Integer, Item> item = new HashMap<>();
        String[] itemTokens = itemtAsText.split(DELIMITER);

        String name = itemTokens[1];
        BigDecimal cost = new BigDecimal(itemTokens[2]);
        Integer amount = Integer.parseInt(itemTokens[3]);

        if (itemTokens[0].equals("DRINK")) {
            item.put(index++, new Drink(name, cost, amount));
        } else {
            item.put(index++, new Snack(name, cost, amount));
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
        Map<Integer, Item> currentItem;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);

            inventory.putAll(currentItem);
        }

        scanner.close();
    }

    private String marshallItem(Item aItem) {
        String itemAsText = aItem.toString();
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

        this.getInventory().forEach((key, value) -> {
            String itemAsText = marshallItem(value);
            out.println(itemAsText);
            out.flush();
        });

        out.close();
    }

}
