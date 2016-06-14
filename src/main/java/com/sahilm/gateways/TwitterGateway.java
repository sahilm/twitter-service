package com.sahilm.gateways;

import com.sahilm.resources.Tweet;

import java.util.List;

public interface TwitterGateway {
    List<Tweet> searchByHashtag(String hashtag);
}
