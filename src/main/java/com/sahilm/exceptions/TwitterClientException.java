package com.sahilm.exceptions;

public class TwitterClientException extends RuntimeException {
    public TwitterClientException(twitter4j.TwitterException cause) {
        super(cause);
    }
}
