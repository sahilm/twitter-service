package com.sahilm.services;

import com.sahilm.gateways.TwitterGateway;
import com.sahilm.resources.Tweet;
import com.sahilm.resources.Tweets;
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

    public Tweets getTweetsByHashtag(String hashtag) {
        List<Tweet> tweetsFromGateway = twitterGateway.queryByHashtag(normalizedHashtag(hashtag));
        return new Tweets(tweetsFromGateway);
    }

    private String normalizedHashtag(String hashtag) {
        if (hashtag.startsWith("#")) {
            return hashtag;
        }
        return "#" + hashtag;
    }
}
