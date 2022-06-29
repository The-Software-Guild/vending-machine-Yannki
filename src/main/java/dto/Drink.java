package dto;

import java.math.BigDecimal;

public class Drink extends Item{

    public Drink(String name, BigDecimal cost) {
        super(name, cost);
    }

    @Override
    public String display() {
        return "Drink: " + name + " " + cost;
    }

}
