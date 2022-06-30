package dao;

import dto.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public interface VendingMachineDao {

    public Item removeItem(Item item);

    public Item addItem(Item item);

    public Map<Item, Integer> getAllItems();

    public Item getItem(int index);

    public BigDecimal getFunds();

    public BigDecimal addMoney(BigDecimal money);

    public BigDecimal change(BigDecimal money);

    public Map<Integer, Item> getProducts();
}
