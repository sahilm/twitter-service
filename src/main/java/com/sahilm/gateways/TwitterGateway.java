package com.sahilm.gateways;

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
public class TwitterGateway {
    private final Twitter twitterClient;

    @Inject
    public TwitterGateway(Twitter twitterClient) {
        this.twitterClient = twitterClient;
    }

    public List<Tweet> queryByHashtag(String hashtag) {
        Query query = new Query(hashtag);
        try {
            QueryResult result = twitterClient.search(query);
            return result
                    .getTweets()
                    .stream().map(status -> new Tweet(status.getText()))
                    .collect(Collectors.toList());
        } catch (TwitterException te) {
            throw new com.sahilm.exceptions.TwitterException(te);
        }
    }
}
