package com.github.gronblack.exchangerates.service;

import com.github.gronblack.exchangerates.clients.ExchangeClient;
import com.github.gronblack.exchangerates.dto.currency.NamedCurrency;
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
public class ExchangeService {
    private static final Logger log = LoggerFactory.getLogger(ExchangeService.class);
    private final ExchangeClient client;

    @Value("${exchange.app_id}")
    private String appId;

    @Value("${exchange.base_currency}")
    private String baseCurrency;

    public ExchangeService(ExchangeClient client) {
        this.client = client;
    }

    public List<NamedCurrency> getCurrencies() {
        log.info("getCurrencies");
        return new TreeMap<>(client.getCurrencies())
                .entrySet().stream()
                .map(entry -> new NamedCurrency(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public Rate getRateOnDate(LocalDate date, String symbols) {
        log.info("getRateOnDate {}, symbols {}", date, symbols);
        String dateString = date + ".json";
        return client.getRateOnDate(dateString, appId, baseCurrency, symbols);
    }

    public Rate getRateLatest(String symbols) {
        log.info("getRateLatest, symbols {}", symbols);
        return client.getRateLatest(appId, baseCurrency, symbols);
    }
}
