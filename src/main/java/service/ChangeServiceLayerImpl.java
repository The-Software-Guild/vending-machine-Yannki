package service;

import dao.VendingMachineAuditDao;
import dao.VendingMachineDao;
import dao.VendingMachineDaoPersistenceException;
import dto.Coin;
import dto.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class ChangeServiceLayerImpl implements ChangeServiceLayer {

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public ChangeServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public Item removeItem(Integer index) throws VendingMachineDaoPersistenceException, NoItemInventoryException {
        Item tempItem = dao.buyItem(index);
        if (tempItem != null) {
            auditDao.writeAuditEntry(tempItem.getName() + " was bought for " + tempItem.getCost());
            return tempItem;
        } else {
            throw new NoItemInventoryException("Item not available");
        }

    }

    @Override
    public Map<Integer, Item> getAllItems() {
        return dao.getInventory();
    }

    @Override
    public Item getItem(int index) throws NoItemInventoryException {
        Map<Integer, Item> list = dao.getInventory();
        try {
            if (dao.getItem(index) != null || list.get(index).getQuantity() > 0) {
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
