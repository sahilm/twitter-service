package com.sahilm.gateways.twitter;

import com.sahilm.exceptions.TwitterClientException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.inject.Inject;

@Component
@Profile("default")
public class TwitterGatewayImpl implements TwitterGateway {

    private final Twitter twitter;

    @Inject
    public TwitterGatewayImpl(final Twitter twitter) {
        this.twitter = twitter;
    }

    @Override
    public TwitterQueryResponse searchByHashtag(final String hashtag) {
        final Query query = new Query(hashtag);
        try {
            final QueryResult result = twitter.search(query);
            return new TwitterQueryResponseImpl(result);
        } catch (TwitterException te) {
            throw new TwitterClientException(te);
        }
    }
}
