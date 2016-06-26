package com.sahilm.endtoend;

import com.jayway.jsonpath.JsonPath;
import com.sahilm.Application;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EndToEndTest {
    private static final String APP_URL = "http://localhost:8080/";
    private static final Logger LOGGER = LoggerFactory.getLogger(EndToEndTest.class);

    @BeforeClass
    public void setUp() throws Exception {
        Application.start(Application.JoinWithMainThread.NO);
    }

    @AfterClass
    public void tearDown() throws Exception {
        Application.stop();
    }

    @Test
    public void shouldReturnTweetsByHashtag() throws Exception {
        Request getTweets = Request.Get(APP_URL + "/tweets?hashtag=docker");
        String response = getTweets.execute().returnContent().asString(StandardCharsets.UTF_8);
        LOGGER.info(response);
        List<String> tweets = JsonPath.read(response, "$..text");
        assertThat(tweets.size()).isGreaterThan(0);
    }


}
