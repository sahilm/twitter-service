package com.sahilm.gateways;

import com.sahilm.resources.Tweet;

import java.util.List;

public interface TwitterClient {
    List<Tweet> searchByHashtag(String hashtag);
}
