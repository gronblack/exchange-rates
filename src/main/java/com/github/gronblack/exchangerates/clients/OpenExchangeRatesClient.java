package com.github.gronblack.exchangerates.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "openExchangeRatesClient", url = "https://openexchangerates.org/api")
public interface OpenExchangeRatesClient {

    @GetMapping("/currencies.json")
    Map<String, String> getCurrencies();
}
