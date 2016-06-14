package com.sahilm.services;

import com.sahilm.gateways.TwitterGateway;
import com.sahilm.resources.Tweet;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class TweetsService {
    private final TwitterGateway twitterGateway;

    @Inject
    public TweetsService(TwitterGateway twitterGateway) {
        this.twitterGateway = twitterGateway;
    }

    public List<Tweet> getTweetsByHashtag(String hashtag) {
        return twitterGateway.searchByHashtag(normalizedHashtag(hashtag));
    }

    private String normalizedHashtag(String hashtag) {
        if (hashtag.startsWith("#")) {
            return hashtag;
        }
        return "#" + hashtag;
    }
}
