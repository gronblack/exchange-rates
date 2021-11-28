package com.github.gronblack.exchangerates.controller;

import com.github.gronblack.exchangerates.dto.Gif;
import com.github.gronblack.exchangerates.dto.Rate;
import com.github.gronblack.exchangerates.exception.ClientsException;
import com.github.gronblack.exchangerates.service.ExchangeService;
import com.github.gronblack.exchangerates.service.GiphyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@RestController
@RequestMapping(value = ExchangeController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class ExchangeController {
    public static final String REST_URL = "/api/gifs";
    private static final Logger log = LoggerFactory.getLogger(ExchangeController.class);
    private final ExchangeService exchangeService;
    private final GiphyService giphyService;

    public ExchangeController(ExchangeService exchangeService, GiphyService giphyService) {
        this.exchangeService = exchangeService;
        this.giphyService = giphyService;
    }

    @GetMapping("/{code}")
    public Gif getGif(@PathVariable @NotBlank @Size(min = 3, max = 3) String code) {
        log.info("getGif with code = {}", code);

        Gif gif;
        try {
            Rate rateYesterday = exchangeService.getRateOnDate(LocalDate.now().minusDays(1), code);
            Rate rateLatest = exchangeService.getRateLatest(code);
            gif = giphyService.getGif(rateYesterday.compareTo(rateLatest));
        } catch (Exception e) {
            throw new ClientsException(e.getMessage());
        }
        return gif;
    }
}
