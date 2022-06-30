package dto;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) && Objects.equals(cost, item.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, getClass());
    }

}
