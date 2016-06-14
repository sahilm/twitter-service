package com.sahilm.gateways;

import com.sahilm.gateways.twitter.TwitterGateway;
import com.sahilm.gateways.twitter.TwitterQueryResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(locations = "classpath:context.xml")
@Test
public class TwitterGatewayIT extends AbstractTestNGSpringContextTests {

    @Inject
    TwitterGateway twitterGateway;

    public void shouldBeAbleToQueryTweetsByHashtag() throws Exception {
        final TwitterQueryResponse twitterQueryResponse = twitterGateway.searchByHashtag("#docker");
        assertThat(twitterQueryResponse.getTweets()).isNotEmpty();
    }
}
