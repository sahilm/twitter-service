package com.sahilm.services;

import com.sahilm.gateways.twitter.TwitterGateway;
import com.sahilm.gateways.twitter.TwitterQueryResponse;
import com.sahilm.resources.Tweet;
import mockit.Mocked;
import mockit.StrictExpectations;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TweetsServiceTest {

    @Test
    public void shouldFetchTweetsFromTwitterGateway() throws Exception {
        final List<String> expectedTweets = Collections.singletonList("#microservices are the best");
        TwitterQueryResponse expectedTwitterResponse = new TwitterQueryResponse() {
            @Override
            public int getCount() {
                return expectedTweets.size();
            }

            @Override
            public List<String> getTweets() {
                return expectedTweets;
            }
        };

        TweetsService tweetsService = new TweetsService(new TwitterGateway() {
            @Override
            public TwitterQueryResponse searchByHashtag(String hashtag) {
                assertThat(hashtag).isEqualTo("#microservices");
                return expectedTwitterResponse;
            }
        });

        final List<Tweet> actualTweets = tweetsService.getTweetsByHashtag("#microservices");
        // I'm writing Clojure in Java :(
        assertThat(actualTweets.stream().map(Tweet::getText)).isEqualTo(expectedTweets);
    }

    @Test
    public void shouldNormalizeHashtags(@Mocked TwitterGateway twitterGateway) throws Exception {
        final String hashtagWithoutHash = "withoutthehash";
        final String hashtagWithHash = "#withhash";

        new StrictExpectations() {{
            twitterGateway.searchByHashtag(withEqual("#" + hashtagWithoutHash));
            twitterGateway.searchByHashtag(withEqual(hashtagWithHash));
        }};

        final TweetsService tweetsService = new TweetsService(twitterGateway);
        tweetsService.getTweetsByHashtag(hashtagWithoutHash);
        tweetsService.getTweetsByHashtag(hashtagWithHash);
    }
}
