package com.github.gronblack.exchangerates.service;

import com.github.gronblack.exchangerates.BaseTest;
import com.github.gronblack.exchangerates.clients.GiphyClient;
import com.github.gronblack.exchangerates.dto.Gif;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.github.gronblack.exchangerates.TestData.GIPHY_RESPONSE_POSITIVE;
import static org.mockito.Mockito.*;

class GiphyServiceTest extends BaseTest {

    @MockBean
    private GiphyClient giphyClient;

    @Autowired
    private GiphyService service;

    @Value("${giphy.positive_tag}")
    private String positive_tag;

    @Test
    void getGif() {
        when(giphyClient.getRandomGif(anyString(), eq(positive_tag)))
                .thenReturn(GIPHY_RESPONSE_POSITIVE);

        Assertions.assertEquals(new Gif(GIPHY_RESPONSE_POSITIVE), service.getGif(1));
        verify(giphyClient, times(1)).getRandomGif(anyString(), eq(positive_tag));
    }
}