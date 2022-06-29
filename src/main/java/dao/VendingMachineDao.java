package dao;

import dto.Item;

import java.math.BigDecimal;
import java.util.HashMap;

public interface VendingMachineDao {

    public Item removeItem();

    public Item addItem();

    public HashMap<Item, BigDecimal> getAllItems();

    public Item getItem();

    public Item vendItem(Item item);

    public BigDecimal fund(BigDecimal money);

    public BigDecimal change(BigDecimal money);
}
