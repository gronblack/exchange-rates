package com.github.gronblack.exchangerates.dto;

import com.github.gronblack.exchangerates.exception.ClientsException;

import java.util.Map;

public final class Gif {
    private final String url;

    public Gif() {
        url = null;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gif gif = (Gif) o;
        return url.equals(gif.url);
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }
}
