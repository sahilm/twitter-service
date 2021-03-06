package com.sahilm;


import com.jayway.jsonpath.JsonPath;
import com.sahilm.gateways.StubTwitterGateway;
import com.sahilm.support.TwitterServiceActiveProfileResolver;
import org.springframework.test.context.ActiveProfiles;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Test
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:context.xml")
@ActiveProfiles("Test")
public class ComponentTest extends AbstractTestNGSpringContextTests {

    @Inject
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @PostConstruct
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void shouldReturnFetchedTweetsByHashtag() throws Exception {
        final List<String> expectedTweets = StubTwitterGateway.TWEETS;

        final MvcResult mvcResult = this.mockMvc.perform(get("/tweets?hashtag=docker"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn();
        final String responseBody = mvcResult.getResponse().getContentAsString();
        final List<String> actual = JsonPath.read(responseBody, "$..text");
        assertThat(actual).isEqualTo(expectedTweets);
    }
}
