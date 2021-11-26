package com.github.gronblack.exchangerates.controller;

import com.github.gronblack.exchangerates.BaseTest;
import com.github.gronblack.exchangerates.clients.ExchangeClient;
import com.github.gronblack.exchangerates.clients.GiphyClient;
import com.github.gronblack.exchangerates.dto.Gif;
import com.github.gronblack.exchangerates.exception.ClientsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static com.github.gronblack.exchangerates.TestData.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ExchangeControllerTest extends BaseTest {

    @MockBean
    private GiphyClient giphyClient;

    @MockBean
    private ExchangeClient exchangeClient;

    @Test
    void clientsError() throws Exception {
        when(exchangeClient.getRateLatest(anyString(), anyString(), anyString()))
                .thenThrow(ClientsException.class);

        perform(get(REST_URL).param("symbols", String.valueOf(JPY)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Nested
    class GetGifTests {
        @Value("${giphy.positive_tag}")
        private String positive_tag;

        @Value("${giphy.negative_tag}")
        private String negative_tag;

        @BeforeEach
        private void prepare() {
            when(giphyClient.getRandomGif(anyString(), eq(positive_tag)))
                    .thenReturn(GIPHY_RESPONSE_POSITIVE);
            when(giphyClient.getRandomGif(anyString(), eq(negative_tag)))
                    .thenReturn(GIPHY_RESPONSE_NEGATIVE);
        }

        @Test
        void getPositiveGif() throws Exception {
            when(exchangeClient.getRateLatest(anyString(), anyString(), eq(JPY.getCode())))
                    .thenReturn(JPY_RATE_TODAY);
            when(exchangeClient.getRateOnDate(anyString(), anyString(), anyString(), eq(JPY.getCode())))
                    .thenReturn(JPY_RATE_YESTERDAY);

            perform(get(REST_URL).param("symbols", String.valueOf(JPY)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(GIF_MATCHER.contentJson(new Gif(GIPHY_RESPONSE_POSITIVE)));
        }

        @Test
        void getNegativeGif() throws Exception {
            when(exchangeClient.getRateLatest(anyString(), anyString(), eq(RUB.getCode())))
                    .thenReturn(RUB_RATE_TODAY);
            when(exchangeClient.getRateOnDate(anyString(), anyString(), anyString(), eq(RUB.getCode())))
                    .thenReturn(RUB_RATE_YESTERDAY);

            perform(get(REST_URL).param("symbols", String.valueOf(RUB)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(GIF_MATCHER.contentJson(new Gif(GIPHY_RESPONSE_NEGATIVE)));
        }
    }
}