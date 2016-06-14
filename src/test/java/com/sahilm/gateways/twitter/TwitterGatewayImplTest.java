package com.sahilm.gateways.twitter;

import com.sahilm.exceptions.TwitterGatewayException;
import mockit.Mocked;
import mockit.StrictExpectations;
import org.testng.annotations.Test;
import twitter4j.Query;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterGatewayImplTest {

    @Test(expectedExceptions = TwitterGatewayException.class)
    public void shouldRaiseTwitterGatewayExceptionOnError(@Mocked final Twitter twitterClient) throws Exception {
        new StrictExpectations() {{
            twitterClient.search(withAny(new Query()));
            result = new TwitterException("failed to query");
        }};

        TwitterGateway twitterGateway = new TwitterGatewayImpl(twitterClient);
        twitterGateway.searchByHashtag("something");

    }
}