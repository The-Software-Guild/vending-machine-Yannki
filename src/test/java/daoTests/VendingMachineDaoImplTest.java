package daoTests;

import dao.VendingMachineDaoImpl;
import dao.VendingMachineDaoPersistenceException;
import dto.Drink;
import dto.Item;
import dto.Snack;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class VendingMachineDaoImplTest {

    private VendingMachineDaoImpl machine;

    public VendingMachineDaoImplTest() throws VendingMachineDaoPersistenceException {
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
        Map<Item, Integer> inventory = new HashMap<>();
        inventory.put(drink, 1);
        inventory.put(snack, 1);
        machine.addItem(drink);
        machine.addItem(snack);
        try {
            Map<Item, Integer> checkItems = machine.getAllItems();
            System.out.println(checkItems);
            assertEquals(checkItems, inventory);
        } catch (Exception e) {
            fail("Something went wrong with adding an item");
        }
    }

    //State test - need to remake
    @Test
    public void addMoneyTest(){
        assertEquals(new BigDecimal("1.25"), machine.addMoney(new BigDecimal("1.25")));
    }

    @Test
    public void changeTest(){
        assertEquals(new BigDecimal("1.25"), machine.change(new BigDecimal("1.25")));
    }
}
