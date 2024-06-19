package com.critchlow.footballdynasty.controllers;


import com.critchlow.footballdynasty.model.Standings;
import com.critchlow.footballdynasty.model.Team;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StandingsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    StandingsService standingsService;

    @MockBean
    StandingsRepository standingsRepository;

    
    @Test
    public void getStandings_thenResultReturned() throws Exception {
        //Given
        Team testTeam = new Team();
        UUID teamId = UUID.randomUUID();
        testTeam.id = teamId;
        testTeam.name = "Team Name";
        Standings testStandings = new Standings();
        UUID standingsId = UUID.randomUUID();
        testStandings.id= standingsId;
        testStandings.team = testTeam;

        //When
        when(standingsRepository.findAll()).thenReturn(List.of(testStandings));

        //Then
        this.mockMvc.perform(get("/api/v1.0/standings"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("[{\"id\":\"%s\",\"team\":{\"id\":\"%s\",\"name\":\"%s\"}}]",standingsId, teamId, testTeam.name)));
        verify(standingsRepository).findAll();
    }
}
