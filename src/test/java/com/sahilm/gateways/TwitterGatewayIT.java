package com.sahilm.gateways;

import com.sahilm.resources.Tweet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(locations = "classpath:context.xml")
@Test
public class TwitterGatewayIT extends AbstractTestNGSpringContextTests {

    @Inject
    TwitterClient twitterClient;

    public void shouldBeAbleToQueryTweetsByHashtag() throws Exception {
        List<Tweet> tweets = twitterClient.searchByHashtag("#docker");
        assertThat(tweets).isNotEmpty();
    }
}
