package dto;

import java.math.BigDecimal;

public abstract class Item {
    String name;
    BigDecimal cost;

    public Item(String name, BigDecimal cost){
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public abstract String display();
}
