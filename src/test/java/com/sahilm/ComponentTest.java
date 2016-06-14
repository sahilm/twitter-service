package com.sahilm;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sahilm.gateways.StubTwitterClient;
import com.sahilm.resources.Tweet;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Test
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:context.xml")
@ActiveProfiles("ComponentTest")
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
        MvcResult result = this.mockMvc.perform(get("/tweets?hashtag=docker"))
                .andExpect(status().isOk())
                .andReturn();

        List<Tweet> actual = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<Tweet>>() {
        });
        assertThat(actual).isEqualTo(StubTwitterClient.TWEETS);
    }
}
