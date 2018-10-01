package com.ranking.controller;

import com.ranking.TestMain;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ParticipantControllerTest extends TestMain {
    private MockMvc mockMvc;

    @Autowired
    private ParticipantController participantController;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(participantController).build();
    }

    @Test
    public void testGetRankingParticipantController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/participants/ranking")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
