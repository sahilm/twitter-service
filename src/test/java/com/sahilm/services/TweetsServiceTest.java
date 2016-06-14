package com.sahilm.services;

import com.sahilm.gateways.TwitterGateway;
import com.sahilm.resources.Tweet;
import com.sahilm.resources.Tweets;
import mockit.Mocked;
import mockit.StrictExpectations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TweetsServiceTest {
    @Mocked
    TwitterGateway twitterGateway;

    private TweetsService tweetsService;

    @BeforeMethod
    public void setUp() throws Exception {
        tweetsService = new TweetsService(twitterGateway);
    }

    @Test
    public void shouldFetchTweetsFromTwitterGateway() throws Exception {

        final String hashtag = "#microservices";
        final List<Tweet> tweetsFromGateway = Arrays.asList(new Tweet("#microservices are the best"));

        new StrictExpectations() {{
            twitterGateway.queryByHashtag(withEqual(hashtag));
            result = tweetsFromGateway;
        }};

        final Tweets expectedTweets = new Tweets(tweetsFromGateway);
        final Tweets actualTweets = tweetsService.getTweetsByHashtag(hashtag);
        assertThat(actualTweets).isEqualTo(expectedTweets);
    }

    @Test
    public void shouldNormalizeHashtags() throws Exception {
        final String hashtagWithoutHash = "withoutthehash";
        final String hashtagWithHash = "#withhash";

        new StrictExpectations() {{
            twitterGateway.queryByHashtag(withEqual("#" + hashtagWithoutHash));
            twitterGateway.queryByHashtag(withEqual(hashtagWithHash));
        }};

        tweetsService.getTweetsByHashtag(hashtagWithoutHash);
        tweetsService.getTweetsByHashtag(hashtagWithHash);
    }
}
