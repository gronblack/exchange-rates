package com.github.gronblack.exchangerates.service;

import com.github.gronblack.exchangerates.clients.GiphyClient;
import com.github.gronblack.exchangerates.dto.Gif;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GiphyService {
    private static final Logger log = LoggerFactory.getLogger(GiphyService.class);
    private final GiphyClient client;

    @Value("${giphy.api_key}")
    private String api_key;

    @Value("${giphy.positive_tag}")
    private String positive_tag;

    @Value("${giphy.negative_tag}")
    private String negative_tag;

    public GiphyService(GiphyClient client) {
        this.client = client;
    }

    public Gif getGif(int tag) {
        log.info("getGif with tag {}", tag);
        return new Gif(client.getRandomGif(api_key, tag < 0 ? negative_tag : positive_tag));
    }
}
