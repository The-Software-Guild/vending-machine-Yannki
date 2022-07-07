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

    public BigDecimal changeQuarters(BigDecimal money);

    public BigDecimal changeNickles(BigDecimal money);

    public BigDecimal changeDimes(BigDecimal money);

    public BigDecimal changePennies(BigDecimal money);
}
