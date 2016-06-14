package com.sahilm.gateways;

import com.sahilm.exceptions.TwitterClientException;
import com.sahilm.resources.Tweet;
import org.springframework.stereotype.Component;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TwitterClientImpl implements TwitterClient {

    private final Twitter twitter;

    @Inject
    public TwitterClientImpl(Twitter twitter) {
        this.twitter = twitter;
    }

    @Override
    public List<Tweet> searchByHashtag(String hashtag) {
        Query query = new Query(hashtag);
        try {
            QueryResult result = twitter.search(query);
            return result
                    .getTweets()
                    .stream().map(status -> new Tweet(status.getText()))
                    .collect(Collectors.toList());
        } catch (TwitterException te) {
            throw new TwitterClientException(te);
        }
    }
}
