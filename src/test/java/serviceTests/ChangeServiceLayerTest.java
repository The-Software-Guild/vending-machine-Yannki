package serviceTests;

import dao.VendingMachineDaoPersistenceException;
import dto.Drink;
import dto.Item;
import org.junit.jupiter.api.Test;
import service.ChangeServiceLayer;
import service.InsufficientFundsException;
import service.NoItemInventoryException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//@ExtendWith(MockitoExtension.class)
public class ChangeServiceLayerTest implements ChangeServiceLayer {
    //@Mock
    private VendingMachineDaoStubImpl machine;

    public ChangeServiceLayerTest() throws VendingMachineDaoPersistenceException {
        machine = new VendingMachineDaoStubImpl();
    }

    @Test
    public void addItemTest(){
        Item drink = new Drink("Coca-cola", new BigDecimal("1.2"),1);
        try {
            Item checkDrink = machine.addItem(drink);
            assertEquals(drink, checkDrink);
        } catch (Exception e) {
            fail("Something went wrong with adding an item");
        }

    }

    @Test
    public void removeItemTest(){
        Item drink = new Drink("Coca-cola", new BigDecimal("1.2"),1);
        machine.addItem(drink);
        try {
            Item checkDrink = machine.buyItem(1);
            assertEquals(drink, checkDrink);
        } catch (Exception e) {
            fail("Something went wrong with adding an item");
        }
    }

    @Test
    public void getAllItemTest(){
        Item drink = new Drink("Coca-cola", new BigDecimal("1.2"),1);
        Map<Item, Integer> inventory = new HashMap<>();
        inventory.put(drink, 1);
        try {
            Map<Integer, Item> checkItems = machine.getInventory();
            assertEquals(inventory,checkItems);
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
        assertEquals(new BigDecimal("-1.25"), machine.change(new BigDecimal("1.25")));
    }

    //Unimportant for now
    @Override
    public Item removeItem(Integer item) throws VendingMachineDaoPersistenceException, NoItemInventoryException {
        return null;
    }

    @Override
    public Map<Integer, Item> getAllItems() {
        return null;
    }

    @Override
    public Item getItem(int index) throws NoItemInventoryException {
        return null;
    }

    @Override
    public BigDecimal addMoney(BigDecimal money) {
        return null;
    }

    @Override
    public BigDecimal transaction(BigDecimal money) throws InsufficientFundsException {
        return null;
    }

    @Override
    public BigDecimal changeQuarters(BigDecimal money) {
        return null;
    }

    @Override
    public BigDecimal changeNickles(BigDecimal money) {
        return null;
    }

    @Override
    public BigDecimal changeDimes(BigDecimal money) {
        return null;
    }

    @Override
    public BigDecimal changePennies(BigDecimal money) {
        return null;
    }
}
