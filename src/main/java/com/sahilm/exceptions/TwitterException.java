package com.sahilm.exceptions;

public class TwitterException extends RuntimeException {
    public TwitterException(twitter4j.TwitterException cause) {
        super(cause);
    }
}
