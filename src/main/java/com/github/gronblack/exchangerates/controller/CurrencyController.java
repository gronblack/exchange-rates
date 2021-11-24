package com.github.gronblack.exchangerates.controller;

import com.github.gronblack.exchangerates.clients.OpenExchangeRatesClient;
import com.github.gronblack.exchangerates.dto.Currency;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CurrencyController {
    private final OpenExchangeRatesClient exchangeClient;

    public CurrencyController(OpenExchangeRatesClient exchangeClient) {
        this.exchangeClient = exchangeClient;
    }

    @GetMapping
    public List<Currency> getCurrencies() {
        TreeMap<String, String> map = new TreeMap<>(exchangeClient.getCurrencies());
        return map.entrySet().stream()
                .map(entry -> new Currency(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @GetMapping("/gif")
    public String getRate() {
        return "gif";
    }
}
