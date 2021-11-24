package com.github.gronblack.exchangerates.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public final class Currency {

    @NotBlank
    @Size(min = 3, max = 3)
    private final String code;

    @NotBlank
    private final String name;

    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
