package com.github.gronblack.exchangerates.dto;

import com.github.gronblack.exchangerates.exception.ClientsException;

import java.util.Map;

public final class Gif {
    private final String url;

    public Gif(Map<String, ?> data, Map<String, ?> meta) {
        if ((int) meta.get("status") != 200) {
            throw new ClientsException(String.valueOf(meta.get("msg")));
        }
        this.url = String.valueOf(data.get("embed_url"));
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Gif{url=" + url + "}";
    }
}
