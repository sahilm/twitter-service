package com.sahilm.controllers;

import com.sahilm.resources.Tweets;
import com.sahilm.services.TweetsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping(
        value = "tweets",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TweetsController {

    private final TweetsService tweetsService;

    @Inject
    public TweetsController(TweetsService tweetsService) {
        this.tweetsService = tweetsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Tweets getTweetsByHashtag(@RequestParam String hashtag) {
        return tweetsService.getTweetsByHashtag(hashtag);
    }
}
