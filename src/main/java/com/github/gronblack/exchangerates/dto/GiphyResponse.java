package com.github.gronblack.exchangerates.dto;

import com.github.gronblack.exchangerates.exception.ClientsException;

import java.util.Map;

public final class GiphyResponse {
    private final Map<String, ?> meta;
    private final Map<String, ?> data;

    public GiphyResponse(Map<String, ?> meta, Map<String, ?> data) {
        if ((int) meta.get("status") != 200) {
            throw new ClientsException(String.valueOf(meta.get("msg")));
        }
        this.meta = meta;
        this.data = data;
    }

    public Map<String, ?> getMeta() {
        return meta;
    }

    public Map<String, ?> getData() {
        return data;
    }
}
