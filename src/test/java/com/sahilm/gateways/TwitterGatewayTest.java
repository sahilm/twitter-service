package com.sahilm.gateways;

import com.sahilm.exceptions.TwitterGatewayException;
import mockit.Mocked;
import mockit.StrictExpectations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import twitter4j.Query;
import twitter4j.Twitter;

public class TwitterGatewayTest {
    @Mocked
    Twitter twitterClient;
    private TwitterGateway twitterGateway;

    @BeforeMethod
    public void setUp() throws Exception {
        twitterGateway = new TwitterGateway(twitterClient);
    }

    @Test(expectedExceptions = TwitterGatewayException.class)
    public void shouldRaiseExceptionWhenFetchingTweetsFails() throws Exception {

        new StrictExpectations() {{
            twitterClient.search(withAny(new Query()));
            result = new twitter4j.TwitterException("Cannot fetch tweets at this time.");
        }};
        twitterGateway.queryByHashtag("#microservices");
    }
}
