package com.github.gronblack.exchangerates.service;

import com.github.gronblack.exchangerates.clients.ExchangeClient;
import com.github.gronblack.exchangerates.dto.NamedCurrency;
import com.github.gronblack.exchangerates.dto.Rate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class CurrencyService {
    private static final Logger log = LoggerFactory.getLogger(CurrencyService.class);
    private final ExchangeClient exchangeClient;

    @Value("${exchange.app_id}")
    private String appId;

    @Value("${exchange.base_currency}")
    private String baseCurrency;

    public CurrencyService(ExchangeClient exchangeClient) {
        this.exchangeClient = exchangeClient;
    }

    public List<NamedCurrency> getCurrencies() {
        log.info("getCurrencies");
        return new TreeMap<>(exchangeClient.getCurrencies())
                .entrySet().stream()
                .map(entry -> new NamedCurrency(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public Rate getRateOnDate(LocalDate date, String symbols) {
        log.info("getRateOnDate {}, symbols {}", date, symbols);
        String dateString = date + ".json";
        return exchangeClient.getRateOnDate(dateString, appId, baseCurrency, symbols);
    }

    public Rate getRateLatest(String symbols) {
        log.info("getRateLatest, symbols {}", symbols);
        return exchangeClient.getRateLatest(appId, baseCurrency, symbols);
    }
}
