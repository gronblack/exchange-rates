package com.github.gronblack.exchangerates.dto;

import java.util.Map;

public class Rate implements Comparable<Rate> {
    private final long timestamp;
    private final Currency baseCurrency;
    private Currency currency;
    private double rate;

    public Rate(long timestamp, String base, Map<String, Double> rates) throws IllegalArgumentException {
        setCurrencyAndRate(rates);
        baseCurrency = new Currency(base);
        this.timestamp = timestamp;
    }

    private void setCurrencyAndRate(Map<String, Double> rates) throws IllegalArgumentException {
        if (rates.isEmpty()) {
            throw new IllegalArgumentException("Map is empty!");
        }
        Map.Entry<String, Double> currencyAndRate = rates.entrySet().iterator().next();
        currency = new Currency(currencyAndRate.getKey());
        rate = currencyAndRate.getValue();
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
