package com.sahilm.exceptions;

public class TwitterClientException extends RuntimeException {
    public TwitterClientException(final twitter4j.TwitterException cause) {
        super(cause);
    }
}
