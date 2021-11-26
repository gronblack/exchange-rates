package com.github.gronblack.exchangerates;

import com.github.gronblack.exchangerates.dto.Gif;
import com.github.gronblack.exchangerates.dto.GiphyResponse;
import com.github.gronblack.exchangerates.dto.Rate;
import com.github.gronblack.exchangerates.dto.currency.Currency;
import com.github.gronblack.exchangerates.dto.currency.NamedCurrency;

import java.time.LocalDate;
import java.util.Map;

public final class TestData {
    public static final MatcherFactory.Matcher<Gif> GIF_MATCHER = MatcherFactory.usingEqualsComparator(Gif.class);

    public static final Currency BASE_CURRENCY = new Currency("USD");
    public static final Currency JPY = new Currency("JPY");
    public static final Currency RUB = new Currency("RUB");

    public static final NamedCurrency JPY_NAMED = new NamedCurrency("JPY", "Japanese Yen");
    public static final NamedCurrency RUB_NAMED = new NamedCurrency("RUB", "Russian Ruble");

    public static final LocalDate DATE_YESTERDAY = LocalDate.now().minusDays(1);

    public static final GiphyResponse GIPHY_RESPONSE_POSITIVE =
            new GiphyResponse(Map.of("status", 200), Map.of("embed_url", "positive-gif-url"));
    public static final GiphyResponse GIPHY_RESPONSE_NEGATIVE =
            new GiphyResponse(Map.of("status", 200), Map.of("embed_url", "negative-gif-url"));

    // JPY - positive scenario
    public static final Rate JPY_RATE_YESTERDAY = new Rate(0, BASE_CURRENCY.getCode(), Map.of(JPY.getCode(), 100.0));
    public static final Rate JPY_RATE_TODAY = new Rate(0, BASE_CURRENCY.getCode(), Map.of(JPY.getCode(), 50.0));

    // RUB - negative scenario
    public static final Rate RUB_RATE_YESTERDAY = new Rate(0, BASE_CURRENCY.getCode(), Map.of(JPY.getCode(), 60.0));
    public static final Rate RUB_RATE_TODAY = new Rate(0, BASE_CURRENCY.getCode(), Map.of(JPY.getCode(), 110.0));
}
