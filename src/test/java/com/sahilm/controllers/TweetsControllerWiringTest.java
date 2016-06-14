package com.sahilm.controllers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sahilm.resources.Tweet;
import com.sahilm.resources.Tweets;
import com.sahilm.services.TweetsService;
import mockit.Mocked;
import mockit.StrictExpectations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Test
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:context.xml")
public class TweetsControllerWiringTest extends AbstractTestNGSpringContextTests {

    @Inject
    private WebApplicationContext wac;

    @Mocked
    private TweetsService tweetsService;

    private MockMvc mockMvc;

    @PostConstruct
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void shouldReturnFetchedTweetsByHashtag() throws Exception {
        Tweets expected = new Tweets(Arrays.asList(new Tweet("#docker is awesome"), new Tweet("#docker is the future")));
        new StrictExpectations() {{
            tweetsService.getTweetsByHashtag(withEqual("docker"));
            result = expected;
        }};

        MvcResult result = this.mockMvc.perform(get("/tweets?hashtag=docker"))
                .andExpect(status().isOk())
                .andReturn();

        Tweets actual = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<Tweets>() {
        });
        assertThat(actual).isEqualTo(expected);
    }
}