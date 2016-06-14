package com.sahilm.resources;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Tweets {

    private List<Tweet> tweets;

    public Tweets() {
    }

    public Tweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<Tweet> getTweets() {
        return Collections.unmodifiableList(tweets);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweets tweets1 = (Tweets) o;
        return Objects.equals(tweets, tweets1.tweets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tweets);
    }
}
