package com.github.gronblack.exchangerates.controller;

import com.github.gronblack.exchangerates.dto.Rate;
import com.github.gronblack.exchangerates.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CurrencyController {
    private static final Logger log = LoggerFactory.getLogger(CurrencyController.class);
    private final CurrencyService service;

    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping
    public String getGif(@RequestParam @NotBlank @Size(min = 3, max = 3) String symbols) {
        log.info("getGif with symbols = {}", symbols);
        Rate rateYesterday = service.getRateOnDate(LocalDate.now().minusDays(1), symbols);
        Rate rateLatest = service.getRateLatest(symbols);

        String result = rateLatest.compareTo(rateYesterday) >= 0 ? "rich" : "broke";
        return result;
    }
}
