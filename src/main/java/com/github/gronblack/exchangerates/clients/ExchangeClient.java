package com.github.gronblack.exchangerates.clients;

import com.github.gronblack.exchangerates.dto.Rates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "exchange", url = "${exchange.base_url}")
public interface ExchangeClient {

    @GetMapping("${exchange.currencies_path}")
    Map<String, String> getCurrencies();

    @GetMapping("${exchange.historical_path}/{date}")
    Rates getRateOnDate(@PathVariable String date, @RequestParam String app_id, @RequestParam String base, @RequestParam String symbols);

    @GetMapping("${exchange.latest_path}")
    Rates getRateLatest(@RequestParam String app_id, @RequestParam String base, @RequestParam String symbols);
}
