package com.sahilm.controllers;

import com.sahilm.resources.Tweet;
import com.sahilm.services.TweetsService;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(
        value = "tweets",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TweetsController {
    private static final org.slf4j.Logger LOGGER =
            LoggerFactory.getLogger(TweetsController.class);
    private final TweetsService tweetsService;

    @Inject
    public TweetsController(final TweetsService tweetsService) {
        this.tweetsService = tweetsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Tweet> getTweetsByHashtag(@RequestParam final String hashtag, HttpServletResponse response) {
        LOGGER.info("Fetching tweets for #: {}", hashtag);
        long startTime = System.nanoTime();
        final List<Tweet> tweets = tweetsService.getTweetsByHashtag(hashtag);
        final double estimatedTime = (double) (System.nanoTime() - startTime);
        response.setHeader("x-runtime", String.valueOf(estimatedTime / 1E9));
        LOGGER.info("Done in {}", estimatedTime);
        return tweets;
    }
}
