package com.sahilm.resources;

import java.util.Objects;

public class Tweet {
    private String text;

    public Tweet() {
    }

    public Tweet(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return Objects.equals(text, tweet.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
