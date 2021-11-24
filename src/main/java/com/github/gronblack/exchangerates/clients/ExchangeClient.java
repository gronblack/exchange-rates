package com.github.gronblack.exchangerates.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "exchange", url = "${exchange.base_url}")
public interface ExchangeClient {

    @GetMapping("${exchange.currencies_path}")
    Map<String, String> getCurrencies();
}
