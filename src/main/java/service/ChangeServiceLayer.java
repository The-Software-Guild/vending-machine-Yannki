package service;

import dto.Item;

import java.math.BigDecimal;
import java.util.HashMap;

public interface ChangeServiceLayer {

    public Item removeItem();

    public Item addItem();

    public HashMap<Item, BigDecimal> getAllItems();

    public Item getItem();

    public BigDecimal fund(BigDecimal money);

    public BigDecimal change(BigDecimal money);
}
