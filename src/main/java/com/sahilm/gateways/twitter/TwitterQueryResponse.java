package com.sahilm.gateways.twitter;

import java.util.List;

public interface TwitterQueryResponse {
    int getCount();
    List<String> getTweets();
}
