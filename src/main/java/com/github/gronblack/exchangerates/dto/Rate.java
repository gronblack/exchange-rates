package com.github.gronblack.exchangerates.dto;

public class Rate implements Comparable<Rate> {
    private final long timestamp;
    private final Currency baseCurrency;
    private final Currency currency;

    private final double rate;

    public Rate(long timestamp, Currency baseCurrency, Currency currency, double rate) {
        this.timestamp = timestamp;
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.rate = rate;
    }

    public boolean equalsByCurrency(Rate o) {
        return baseCurrency.equals(o.getBaseCurrency()) && currency.equals(o.getCurrency());
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public int compareTo(Rate o) {
        if (!equalsByCurrency(o)) {
            throw new IllegalArgumentException(this + " and " + o + " are not allowed to compare!");
        }
        return Double.compare(rate, o.getRate());
    }
}
