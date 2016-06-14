package com.sahilm.services;

import com.sahilm.gateways.TwitterClient;
import com.sahilm.resources.Tweet;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class TweetsService {
    private final TwitterClient twitterClient;

    @Inject
    public TweetsService(TwitterClient twitterClient) {
        this.twitterClient = twitterClient;
    }

    public List<Tweet> getTweetsByHashtag(String hashtag) {
        return twitterClient.searchByHashtag(normalizedHashtag(hashtag));
    }

    private String normalizedHashtag(String hashtag) {
        if (hashtag.startsWith("#")) {
            return hashtag;
        }
        return "#" + hashtag;
    }
}
