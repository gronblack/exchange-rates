package com.github.gronblack.exchangerates.clients;

import com.github.gronblack.exchangerates.dto.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphy", url = "${giphy.base_url}")
public interface GiphyClient {

    @GetMapping
    Gif getRandomGif(@RequestParam String api_key, @RequestParam String tag);
}
