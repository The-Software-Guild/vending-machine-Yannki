package service;

import dao.VendingMachineDaoPersistenceException;
import dto.Item;

import java.math.BigDecimal;
import java.util.Map;

public interface ChangeServiceLayer {

    public Item removeItem(Integer index) throws VendingMachineDaoPersistenceException, NoItemInventoryException;

    public Map<Integer, Item> getAllItems();

    public Item getItem(int index) throws NoItemInventoryException;

    public BigDecimal addMoney(BigDecimal money);

    public BigDecimal transaction(BigDecimal money) throws InsufficientFundsException;

}
