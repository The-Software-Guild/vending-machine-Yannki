package dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum Coin {
    QUARTER(0.25), NICKLE(0.05), DIME(0.1), PENNY(0.01);

    private BigDecimal value;
    Coin(double value) {
        this.value = new BigDecimal(String.valueOf(value));
    }

    public BigDecimal getValue() {
        return value;
    }

    public static BigDecimal changeQuarters(BigDecimal money) {
        BigDecimal quarter = Coin.QUARTER.getValue();
        BigDecimal amount =  money.divide(quarter).setScale(0, RoundingMode.HALF_UP);
        return amount.compareTo(new BigDecimal("0")) != -1 ? amount : new BigDecimal("0");
    }

    public static BigDecimal changeNickles(BigDecimal money) {
        BigDecimal nickle = Coin.NICKLE.getValue();
        BigDecimal amount = money.divide(nickle).setScale(0,RoundingMode.HALF_UP);
        return amount.compareTo(new BigDecimal("0")) != -1 ? amount : new BigDecimal("0");
    }

    public static BigDecimal changeDimes(BigDecimal money) {
        BigDecimal dime = Coin.DIME.getValue();
        BigDecimal amount = money.divide(dime).setScale(0, RoundingMode.HALF_UP);
        return amount.compareTo(new BigDecimal("0")) != -1 ? amount : new BigDecimal("0");
    }

    public static BigDecimal changePennies(BigDecimal money) {
        BigDecimal penny = Coin.PENNY.getValue();
        BigDecimal amount = money.divide(penny).setScale(0, RoundingMode.HALF_UP);
        return amount.compareTo(new BigDecimal("0")) != -1 ? amount : new BigDecimal("0");
    }
}
