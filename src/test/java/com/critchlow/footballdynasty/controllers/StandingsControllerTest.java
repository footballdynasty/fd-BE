package com.critchlow.footballdynasty.controllers;


import com.critchlow.footballdynasty.dto.Standings;
import com.critchlow.footballdynasty.repository.StandingsRepository;
import com.critchlow.footballdynasty.services.StandingsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StandingsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StandingsService standingsService;

    @MockBean
    StandingsRepository standingsRepository;

    
    @Test
    public void getStandings_thenResultReturned() throws Exception {
        Standings testStandings = new Standings();
        testStandings.id= UUID.randomUUID();
        when(standingsRepository.findAll()).thenReturn(List.of(testStandings));
        this.mockMvc.perform(get("/api/v1.0/standings"))
                .andExpect(status().isOk())
                .andExpect(content().string("berni eats loud"));
    }
}
