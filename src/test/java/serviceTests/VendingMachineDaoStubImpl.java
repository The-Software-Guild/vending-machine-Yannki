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
    public Map<Item, BigDecimal> getAllItems() {
        Map<Item, BigDecimal> inventory = new HashMap<>();
        inventory.put(onlyItem, new BigDecimal(1));
        return inventory;
    }

    @Override
    public Item getItem(Item item) {
        if (item.equals(onlyItem)) {
            return item;
        } else {
            return null;
        }
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
    public BigDecimal fund(BigDecimal money) {
        return null;
    }

    @Override
    public BigDecimal change(BigDecimal money) {
        return null;
    }



}
