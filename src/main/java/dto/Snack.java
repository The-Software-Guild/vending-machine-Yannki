package dto;

import dao.VendingMachineDaoImpl;

import java.math.BigDecimal;

public class Snack extends Item{
    public Snack(String name, BigDecimal cost) {
        super(name, cost);
    }

    @Override
    public String display() {
        return "Snack: " + name + " " + cost;
    }

    @Override
    public String toString() {
        return "SNACK"+ VendingMachineDaoImpl.DELIMITER + name + VendingMachineDaoImpl.DELIMITER + cost;
    }
}
