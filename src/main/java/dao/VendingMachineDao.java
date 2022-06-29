package dao;

import dto.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public interface VendingMachineDao {

    public Item removeItem(Item item);

    public Item addItem(Item item);

    public Map<Item, BigDecimal> getAllItems();

    public Item getItem(Item item);

    public BigDecimal fund(BigDecimal money);

    public BigDecimal change(BigDecimal money);
}
