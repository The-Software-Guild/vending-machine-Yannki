package service;

import dao.VendingMachineDao;
import dao.VendingMachineDaoPersistenceException;
import dto.Item;

import java.math.BigDecimal;
import java.util.Map;

public class ChangeServiceLayerImpl implements ChangeServiceLayer {

    private VendingMachineDao dao;

    public ChangeServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public Item removeItem(Item item) throws VendingMachineDaoPersistenceException {
        return dao.removeItem(item);
    }

    @Override
    public Map<Item, Integer> getAllItems() {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(int index) throws NoItemInventoryException {
        Map<Item, Integer> list = dao.getAllItems();
        try {
            if (dao.getItem(index) != null || list.get(dao.getItem(index)) > 0) {
                return dao.getItem(index);
            } else {
                throw new NoItemInventoryException("Item was not found.");
            }
        } catch (NullPointerException e) {
            throw new NoItemInventoryException("Item does not exist");
        }

    }

    @Override
    public BigDecimal addMoney(BigDecimal money) {
        if (money.compareTo(new BigDecimal("0")) > -1) {
            return dao.addMoney(money);
        }
        return new BigDecimal("0");
    }

    @Override
    public BigDecimal transaction(BigDecimal money) throws InsufficientFundsException{
        if (dao.getFunds().compareTo(money) != -1){
            return dao.change(money);
        } else {
            throw new InsufficientFundsException("Insufficient funds for the chosen item");
        }
    }
}
