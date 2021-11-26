package com.github.gronblack.exchangerates.service;

import com.github.gronblack.exchangerates.BaseTest;
import com.github.gronblack.exchangerates.clients.ExchangeClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Map;

import static com.github.gronblack.exchangerates.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ExchangeServiceTest extends BaseTest {

    @MockBean
    private ExchangeClient exchangeClient;

    @Autowired
    private ExchangeService service;

    @Test
    void getCurrencies() {
        when(exchangeClient.getCurrencies())
            .thenReturn(Map.of(JPY_NAMED.getCode(), JPY_NAMED.getName(), RUB_NAMED.getCode(), RUB_NAMED.getName()));

        assertEquals(List.of(JPY_NAMED, RUB_NAMED), service.getCurrencies());
        verify(exchangeClient, times(1)).getCurrencies();
    }

    @Test
    void getRateOnDate() {
        when(exchangeClient.getRateOnDate(eq(DATE_YESTERDAY.toString()), anyString(), anyString(), eq(JPY.getCode())))
                .thenReturn(JPY_RATE_YESTERDAY);

        assertEquals(JPY_RATE_YESTERDAY, service.getRateOnDate(DATE_YESTERDAY, JPY.getCode()));
        verify(exchangeClient, times(1))
                .getRateOnDate(eq(DATE_YESTERDAY.toString()), anyString(), anyString(), eq(JPY.getCode()));
    }

    @Test
    void getRateLatest() {
        when(exchangeClient.getRateLatest(anyString(), anyString(), eq(JPY.getCode())))
                .thenReturn(JPY_RATE_TODAY);

        assertEquals(JPY_RATE_TODAY, service.getRateLatest(JPY.getCode()));
        verify(exchangeClient, times(1))
                .getRateLatest(anyString(), anyString(), eq(JPY.getCode()));
    }
}