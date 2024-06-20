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
        testTeam.coach = "Team Coach";
        testTeam.conference = "Team Conference";
        Standings testStandings = new Standings();
        int wins = 0;
        int losses = 0;
        Integer rank = null;
        Integer receiving_votes = null;
        UUID standingsId = UUID.randomUUID();
        testStandings.id= standingsId;
        testStandings.team = testTeam;
        int year = 2024;
        testStandings.year = year;
        testStandings.wins = 0;
        testStandings.losses = 0;
        testStandings.rank = null;
        testStandings.receiving_votes = null;

        //When
        when(standingsRepository.findAll()).thenReturn(List.of(testStandings));

        //Then
        this.mockMvc.perform(get("/api/v1.0/standings"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("[{\"id\":\"%s\",\"team\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\"},\"year\":%s,\"wins\":%s,\"losses\":%s,\"rank\":%s,\"receiving_votes\":%s}]",standingsId, teamId, testTeam.name, testTeam.coach, testTeam.conference, year, wins, losses, rank, receiving_votes)));
        verify(standingsRepository).findAll();
    }
}
