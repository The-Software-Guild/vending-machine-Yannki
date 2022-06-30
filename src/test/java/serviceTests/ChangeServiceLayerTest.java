package serviceTests;

import dao.VendingMachineDaoImpl;
import dto.Drink;
import dto.Item;
import dto.Snack;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class ChangeServiceLayerTest {
    private VendingMachineDaoImpl machine;

    public ChangeServiceLayerTest(){
        machine = new VendingMachineDaoImpl();
    }

    @Test
    public void addItemTest(){
        Item drink = new Drink("Coca-cola", new BigDecimal("1.2"));
        try {
            Item checkDrink = machine.addItem(drink);
            assertEquals(drink, checkDrink);
        } catch (Exception e) {
            fail("Something went wrong with adding an item");
        }

    }

    @Test
    public void removeItemTest(){
        Item drink = new Drink("Coca-cola", new BigDecimal("1.2"));
        machine.addItem(drink);
        try {
            Item checkDrink = machine.removeItem(drink);
            assertEquals(drink, checkDrink);
        } catch (Exception e) {
            fail("Something went wrong with adding an item");
        }
    }

    @Test
    public void getAllItemTest(){
        Item drink = new Drink("Coca-cola", new BigDecimal("1.2"));
        Item snack = new Snack("Twix", new BigDecimal("1.2"));
        HashMap<Item, BigDecimal> inventory = new HashMap<>();
        inventory.put(drink, new BigDecimal(1));
        inventory.put(snack, new BigDecimal(1));
        machine.addItem(drink);
        machine.addItem(snack);
        try {
            Map<Item, Integer> checkItems = machine.getAllItems();
            assertEquals(checkItems, inventory);
        } catch (Exception e) {
            fail("Something went wrong with adding an item");
        }
    }

    @Test
    public void fundTest(){
        assertEquals(new BigDecimal("1.25"), machine.addMoney(new BigDecimal("1.25")));
    }

    @Test
    public void changeTest(){
        assertEquals(new BigDecimal("1.25"), machine.change(new BigDecimal("1.25")));
    }
}
