package com.sahilm.gateways;

import com.sahilm.resources.Tweet;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Profile("ComponentTest")
public class StubTwitterClient implements TwitterClient {
    public final static List<Tweet> TWEETS = Collections.unmodifiableList(
            Arrays.asList(new Tweet("#docker is the best"), new Tweet("#microservices for the win")));

    @Override
    public List<Tweet> searchByHashtag(String hashtag) {
        return TWEETS;
    }
}
