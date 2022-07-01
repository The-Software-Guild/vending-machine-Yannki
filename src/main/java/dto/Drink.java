package dto;

import dao.VendingMachineDaoImpl;

import java.math.BigDecimal;

public class Drink extends Item{

    public Drink(String name, BigDecimal cost) {
        super(name, cost);
    }

    @Override
    public String display() {
        return "Drink: " + name + " " + cost;
    }

    @Override
    public String toString() {
        return "DRINK"+ VendingMachineDaoImpl.DELIMITER + name + VendingMachineDaoImpl.DELIMITER + cost;
    }
}
