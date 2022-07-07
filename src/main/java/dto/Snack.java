package dto;

import dao.VendingMachineDaoImpl;

import java.math.BigDecimal;

public class Snack extends Item {
    public Snack(String name, BigDecimal cost, int quantity) {
        super(name, cost, quantity);
    }

    @Override
    public String display() {
        return "Snack: " + name + " " + cost + " Amount: " + quantity;
    }

    @Override
    public String toString() {
        String delimiter = VendingMachineDaoImpl.DELIMITER;
        return "SNACK" + delimiter + name + delimiter + cost + delimiter + quantity;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
