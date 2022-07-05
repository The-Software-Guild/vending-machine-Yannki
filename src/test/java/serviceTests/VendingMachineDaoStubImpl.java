package serviceTests;

import dao.VendingMachineDao;
import dto.Drink;
import dto.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineDaoStubImpl implements VendingMachineDao {

    public Item onlyItem;

    public VendingMachineDaoStubImpl() {
        this.onlyItem = new Drink("Coca-cola", new BigDecimal("1.2"));
    }

    @Override
    public Item addItem(Item item) {
        if (item.equals(onlyItem)) {
            return item;
        } else {
            return null;
        }
    }

    @Override
    public Map<Item, Integer> getAllItems() {
        Map<Item, Integer> inventory = new HashMap<>();
        inventory.put(onlyItem, 1);
        return inventory;
    }

    @Override
    public Item getItem(int index) {
        return null;
    }

    @Override
    public BigDecimal getFunds() {
        return null;
    }

    @Override
    public Item removeItem(Item item) {
        if (item.equals(onlyItem)) {
            return item;
        } else {
            return null;
        }
    }

    @Override
    public BigDecimal addMoney(BigDecimal money) {
        return money;
    }

    @Override
    public BigDecimal change(BigDecimal money) {
        return money.negate();
    }

    @Override
    public Map<Integer, Item> getProducts() {
        return null;
    }


}
