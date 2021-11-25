package com.github.gronblack.exchangerates.dto;

import javax.validation.constraints.NotBlank;

public final class NamedCurrency extends Currency {

    @NotBlank
    private final String name;

    public NamedCurrency(String code, String name) {
        super(code);
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + name;
    }
}
