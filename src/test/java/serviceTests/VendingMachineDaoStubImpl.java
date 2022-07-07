package serviceTests;

import dao.VendingMachineDao;
import dto.Drink;
import dto.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class VendingMachineDaoStubImpl implements VendingMachineDao {

    public Item onlyItem;

    public VendingMachineDaoStubImpl() {
        this.onlyItem = new Drink("Coca-cola", new BigDecimal("1.2"),1);
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
    public Map<Integer, Item> getInventory() {
        Map<Integer, Item> inventory = new HashMap<>();
        inventory.put(1,onlyItem);
        return inventory;
    }

    @Override
    public Item getItem(Integer index) {
        return null;
    }

    @Override
    public BigDecimal getFunds() {
        return null;
    }

    @Override
    public Item buyItem(Integer index) {
        if (onlyItem != null) {
            return onlyItem;
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
