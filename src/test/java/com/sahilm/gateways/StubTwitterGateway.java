package com.sahilm.gateways;

import com.sahilm.gateways.twitter.TwitterGateway;
import com.sahilm.gateways.twitter.TwitterQueryResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Profile("ComponentTest")
public class StubTwitterGateway implements TwitterGateway {
    public final static List<String> TWEETS = Collections.unmodifiableList(
            Arrays.asList("#docker is the best", "#microservices for the win"));

    @Override
    public TwitterQueryResponse searchByHashtag(String hashtag) {
        return new TwitterQueryResponse() {
            @Override
            public int getCount() {
                return TWEETS.size();
            }

            @Override
            public List<String> getTweets() {
                return TWEETS;
            }
        };
    }
}
