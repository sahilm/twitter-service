package com.sahilm.services;

import com.sahilm.gateways.TwitterGateway;
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
        final List<Tweet> expectedTweets = Collections.singletonList(new Tweet("#microservices are the best"));
        TweetsService tweetsService = new TweetsService(new TwitterGateway() {
            @Override
            public List<Tweet> searchByHashtag(String hashtag) {
                assertThat(hashtag).isEqualTo("#microservices");
                return expectedTweets;
            }
        });

        final List<Tweet> actualTweets = tweetsService.getTweetsByHashtag("#microservices");
        assertThat(actualTweets).isEqualTo(expectedTweets);
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
