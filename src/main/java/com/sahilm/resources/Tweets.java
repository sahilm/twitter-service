package com.sahilm.resources;

import java.util.Collections;
import java.util.List;

public class Tweets {

    private final List<Tweet> tweets;

    public Tweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<Tweet> getTweets() {
        return Collections.unmodifiableList(tweets);
    }
}
