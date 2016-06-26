package com.sahilm.services;

import com.sahilm.gateways.twitter.TwitterGateway;
import com.sahilm.gateways.twitter.TwitterQueryResponse;
import com.sahilm.resources.Tweet;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TweetsService {
    private final TwitterGateway twitterGateway;

    @Inject
    public TweetsService(final TwitterGateway twitterGateway) {
        this.twitterGateway = twitterGateway;
    }

    @Cacheable("tweets")
    public List<Tweet> getTweetsByHashtag(final String hashtag) {
        final TwitterQueryResponse response = twitterGateway.searchByHashtag(normalizedHashtag(hashtag));
        return response.getTweets().
                stream().
                map(Tweet::new).
                collect(Collectors.toList());
    }

    private String normalizedHashtag(final String hashtag) {
        if (hashtag.startsWith("#")) {
            return hashtag;
        }
        return "#" + hashtag;
    }
}
