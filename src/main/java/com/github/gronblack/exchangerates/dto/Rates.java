package com.github.gronblack.exchangerates.dto;

import javax.validation.constraints.*;
import java.util.Map;

public final class Rates {

    @Positive
    private final long timestamp;

    private final Currency base;

    @NotEmpty
    private final Map<String, Double> rates;

    public Rates(long timestamp, String base, Map<String, Double> rates) {
        this.timestamp = timestamp;
        this.base = new Currency(base);
        this.rates = rates;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Currency getBase() {
        return base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}
