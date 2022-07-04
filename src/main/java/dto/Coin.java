package dto;

import java.math.BigDecimal;

public enum Coin {
    QUARTER(0.25), NICKLE(0.05), DIME(0.1), PENNY(0.01);

    private BigDecimal value;
    Coin(double value) {
        this.value = new BigDecimal(String.valueOf(value));
    }

    public BigDecimal getValue() {
        return value;
    }
}
