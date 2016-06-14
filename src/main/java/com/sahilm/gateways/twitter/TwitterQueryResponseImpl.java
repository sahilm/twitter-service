package com.sahilm.gateways.twitter;

import twitter4j.QueryResult;
import twitter4j.Status;

import java.util.List;
import java.util.stream.Collectors;

public class TwitterQueryResponseImpl implements TwitterQueryResponse {
    private final QueryResult twitterClientResponse;

    public TwitterQueryResponseImpl(QueryResult twitterClientResponse) {
        this.twitterClientResponse = twitterClientResponse;
    }

    @Override
    public int getCount() {
        return twitterClientResponse.getCount();
    }

    @Override
    public List<String> getTweets() {
        return twitterClientResponse.getTweets()
                .stream()
                .map(Status::getText)
                .collect(Collectors.toList());
    }
}
