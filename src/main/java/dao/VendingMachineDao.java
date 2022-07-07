package dao;

import dto.Item;

import java.math.BigDecimal;
import java.util.Map;

public interface VendingMachineDao {

    public Item buyItem(Integer index) throws VendingMachineDaoPersistenceException;

    public Item addItem(Item item);

    public Map<Integer, Item> getInventory();

    public Item getItem(Integer index);

    public BigDecimal getFunds();

    public BigDecimal addMoney(BigDecimal money);

    public BigDecimal change(BigDecimal money);

    public Map<Integer, Item> getProducts();
}
