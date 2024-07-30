package com.critchlow.footballdynasty.controllers;


import com.critchlow.footballdynasty.model.Standings;
import com.critchlow.footballdynasty.model.Team;
import com.critchlow.footballdynasty.repository.StandingsRepository;
import com.critchlow.footballdynasty.services.StandingsService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class StandingsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    StandingsService standingsService;

    @MockBean
    StandingsRepository standingsRepository;

    @LocalServerPort
    private Integer port;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    
    @Test
    public void getStandings_thenResultReturned() throws Exception {
        //Given
        Team team1 = createTeam("test 1", "coach 1", "conference 1", false, "image1");
        Standings standings1 = createStandings(team1, 0, 0, 2024, null, null);

        Team team2 = createTeam("test 2", "coach 2", "conference 2", false, "image2"
        Standings standings2 = createStandings(team2, 0, 0, 2023, null, null);

        //When
        when(standingsRepository.findAll()).thenReturn(List.of(standings1, standings2));

        //Then
        this.mockMvc.perform(get("/api/v1.0/standings"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("[{\"id\":\"%s\",\"team\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\"},\"year\":%s,\"wins\":%s,\"losses\":%s,\"rank\":%s,\"receiving_votes\":%s},{\"id\":\"%s\",\"team\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\"},\"year\":%s,\"wins\":%s,\"losses\":%s,\"rank\":%s,\"receiving_votes\":%s}]",standings1.id, team1.id, team1.name, team1.coach, team1.conference, 2024, 0, 0, null, null,standings2.id, team2.id, team2.name, team2.coach, team2.conference, 2023, 0, 0, null, null)));
        verify(standingsRepository).findAll();
    }

    @Test
    public void getStandings_yearIsZero_thenResultReturned() throws Exception {
        //Given
        Team team1 = createTeam("test 1", "coach 1", "conference 1");
        Standings standings1 = createStandings(team1, 0, 0, 2024, null, null);

        Team team2 = createTeam("test 2", "coach 2", "conference 2");
        Standings standings2 = createStandings(team2, 0, 0, 2023, null, null);

        //When
        when(standingsRepository.findAll()).thenReturn(List.of(standings1, standings2));

        //Then
        this.mockMvc.perform(get("/api/v1.0/standings?year=0"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("[{\"id\":\"%s\",\"team\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\"},\"year\":%s,\"wins\":%s,\"losses\":%s,\"rank\":%s,\"receiving_votes\":%s},{\"id\":\"%s\",\"team\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\"},\"year\":%s,\"wins\":%s,\"losses\":%s,\"rank\":%s,\"receiving_votes\":%s}]",standings1.id, team1.id, team1.name, team1.coach, team1.conference, 2024, 0, 0, null, null,standings2.id, team2.id, team2.name, team2.coach, team2.conference, 2023, 0, 0, null, null)));
        verify(standingsRepository).findAll();
    }
    @Test
    public void getStandings_yearIsTwentyTwentyThree_thenResultReturned() throws Exception {
        //Given
        Team team1 = createTeam("test 1", "coach 1", "conference 1");
        Standings standings1 = createStandings(team1, 0, 0, 2024, null, null);

        Team team2 = createTeam("test 2", "coach 2", "conference 2");
        Standings standings2 = createStandings(team2, 0, 0, 2023, null, null);

        //When
        when(standingsRepository.findAll()).thenReturn(List.of(standings1, standings2));

        //Then
        this.mockMvc.perform(get("/api/v1.0/standings?year=2023"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("[{\"id\":\"%s\",\"team\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\"},\"year\":%s,\"wins\":%s,\"losses\":%s,\"rank\":%s,\"receiving_votes\":%s}]",standings2.id, team2.id, team2.name, team2.coach, team2.conference, 2023, 0, 0, null, null)));
        verify(standingsRepository).findAll();
    }

    @Test
    public void getStandings_yearIsOne_thenResultReturned() throws Exception {
        //Given
        Team team1 = createTeam("test 1", "coach 1", "conference 1");
        Standings standings1 = createStandings(team1, 0, 0, 2024, null, null);

        Team team2 = createTeam("test 2", "coach 2", "conference 2");
        Standings standings2 = createStandings(team2, 0, 0, 2023, null, null);

        //When
        when(standingsRepository.findAll()).thenReturn(List.of(standings1, standings2));

        //Then
        this.mockMvc.perform(get("/api/v1.0/standings?year=1"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
        verify(standingsRepository).findAll();
    }

    private Team createTeam(String name, String coach, String conference, Boolean isHuman, String imageUrl) {
        Team testTeam = new Team();
        UUID teamId = UUID.randomUUID();
        testTeam.id = teamId;
        testTeam.name = name;
        testTeam.coach = coach;
        testTeam.conference = conference;
        testTeam.isHuman = isHuman;
        testTeam.imageUrl = imageUrl;

        return testTeam;
    }

    private Standings createStandings(Team team, int wins, int losses, int year, Integer rank, Integer receiving_votes){
        Standings testStandings = new Standings();
        UUID standingsId = UUID.randomUUID();
        testStandings.id= standingsId;
        testStandings.team = team;
        testStandings.year = year;
        testStandings.wins = wins;
        testStandings.losses = losses;
        testStandings.rank = rank;
        testStandings.receiving_votes = receiving_votes;
        return testStandings;
    }
}
