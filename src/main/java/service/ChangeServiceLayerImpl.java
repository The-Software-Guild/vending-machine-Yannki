package service;

import dao.VendingMachineAuditDao;
import dao.VendingMachineDao;
import dao.VendingMachineDaoPersistenceException;
import dto.Item;

import java.math.BigDecimal;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeServiceLayerImpl implements ChangeServiceLayer {

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public ChangeServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public Item removeItem(Item item) throws VendingMachineDaoPersistenceException, NoItemInventoryException {
        Item tempItem = dao.removeItem(item);
        if (tempItem != null) {
            auditDao.writeAuditEntry(item.getName() + " was bought for " + item.getCost());
            return tempItem;
        } else {
            throw new NoItemInventoryException("Item not available");
        }

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
