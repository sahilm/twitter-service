package com.sahilm.exceptions;

public class TwitterGatewayException extends RuntimeException {
    public TwitterGatewayException(final twitter4j.TwitterException cause) {
        super(cause);
    }
}
