package com.github.gronblack.exchangerates.dto.currency;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Currency {

    @NotBlank
    @Size(min = 3, max = 3)
    private final String code;

    public Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;
        return code.equals(currency.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
