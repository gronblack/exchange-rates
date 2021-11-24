package com.github.gronblack.exchangerates.service;

import com.github.gronblack.exchangerates.clients.ExchangeClient;
import com.github.gronblack.exchangerates.dto.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class CurrencyService {
    private static final Logger log = LoggerFactory.getLogger(CurrencyService.class);
    private final ExchangeClient exchangeClient;

    public CurrencyService(ExchangeClient exchangeClient) {
        this.exchangeClient = exchangeClient;
    }

    public List<Currency> getCurrencies() {
        log.info("getCurrencies");
        return new TreeMap<>(exchangeClient.getCurrencies())
                .entrySet().stream()
                .map(entry -> new Currency(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
