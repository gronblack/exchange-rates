package com.github.gronblack.exchangerates.dto.currency;

import javax.validation.constraints.NotBlank;

public final class NamedCurrency extends Currency {

    @NotBlank
    private final String name;

    public NamedCurrency(String code, String name) {
        super(code);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + name;
    }
}
