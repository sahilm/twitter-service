package com.sahilm.gateways.twitter;

public interface TwitterGateway {
    TwitterQueryResponse searchByHashtag(final String hashtag);
}
