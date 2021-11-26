package com.github.gronblack.exchangerates.dto;

public final class Gif {
    private String url;

    private Gif() {
    }

    public Gif(GiphyResponse response) {
        this.url = String.valueOf(response.getData().get("embed_url"));
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
