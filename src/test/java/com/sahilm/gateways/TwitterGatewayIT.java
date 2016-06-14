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
    TwitterGateway twitterGateway;

    public void shouldBeAbleToQueryTweetsByHashtag() throws Exception {
        List<Tweet> tweets = twitterGateway.searchByHashtag("#docker");
        assertThat(tweets).isNotEmpty();
    }
}
