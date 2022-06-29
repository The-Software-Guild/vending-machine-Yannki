package dto;

import java.math.BigDecimal;

public class Snack extends Item{
    public Snack(String name, BigDecimal cost) {
        super(name, cost);
    }

    @Override
    public String display() {
        return null;
    }
}
