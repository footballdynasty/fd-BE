package com.critchlow.footballdynasty.controllers;

import com.critchlow.footballdynasty.model.Game;
import com.critchlow.footballdynasty.model.Week;
import com.critchlow.footballdynasty.model.Team;
import com.critchlow.footballdynasty.repository.GameRepository;
import com.critchlow.footballdynasty.repository.WeekRepository;
import com.critchlow.footballdynasty.services.ScheduleService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WeekControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    ScheduleService scheduleService;

    @MockBean
    GameRepository gameRepository;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );
    @Autowired
    private WeekRepository weekRepository;

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
    public void getSchedule_thenResultReturned() throws Exception {
        //Given
        Team team1 = createTeam("test 1", "coach 1", "conference 1", true, "image1");
        Team team2 = createTeam("test 2", "coach 2", "conference 2", false, "image2");

        Week week = createWeek(2024, 1);
        Game game1 = createGame(team1, team2, Date.valueOf("2024-01-01"), 0, 0, week);
        Game game2 = createGame(team2, team1, Date.valueOf("2024-01-02"), 0, 0, week);

        //When
        List<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);
        when(gameRepository.findGamesByYear(2024)).thenReturn(games);

        //Then
        String expectedResponse = String.format(
                "[{\"id\":\"%s\",\"homeTeam\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\",\"imageUrl\":\"%s\",\"username\":\"\",\"isHuman\":%s},\"awayTeam\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\",\"imageUrl\":\"%s\",\"username\":\"\",\"isHuman\":%s},\"date\":\"%s\",\"homeScore\":%s,\"awayScore\":%s,\"homeTeamRank\":0,\"awayTeamRank\":0,\"schedule\":{\"id\":\"%s\",\"year\":%s}},{\"id\":\"%s\",\"homeTeam\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\",\"imageUrl\":\"%s\",\"username\":\"\",\"isHuman\":%s},\"awayTeam\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\",\"imageUrl\":\"%s\",\"username\":\"\",\"isHuman\":%s},\"date\":\"%s\",\"homeScore\":%s,\"awayScore\":%s,\"homeTeamRank\":0,\"awayTeamRank\":0,\"schedule\":{\"id\":\"%s\",\"year\":%s}}]",
                game1.id, team1.id, team1.name, team1.coach, team1.conference, team1.imageUrl, team1.isHuman, team2.id, team2.name, team2.coach, team2.conference, team2.imageUrl, team2.isHuman, game1.date, game1.homeScore, game1.awayScore, week.id, week.year,
                game2.id, team2.id, team2.name, team2.coach, team2.conference, team2.imageUrl, team2.isHuman, team1.id, team1.name, team1.coach, team1.conference, team1.imageUrl, team1.isHuman, game2.date, game2.homeScore, game2.awayScore, week.id, week.year
        );

        this.mockMvc.perform(get("/api/v1.0/schedule"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
    @Test
    public void getScheduleYearIsTwentyTwentyFour_thenResultReturned() throws Exception {
        //Given
        Team team1 = createTeam("test 1", "coach 1", "conference 1", true, "image1");
        Team team2 = createTeam("test 2", "coach 2", "conference 2", false, "image2");

        Week week = createWeek(2024, 1);
        Game game1 = createGame(team1, team2, Date.valueOf("2024-01-01"), 0, 0, week);
        Game game2 = createGame(team2, team1, Date.valueOf("2024-01-02"), 0, 0, week);

        //When
        when(gameRepository.findGamesByYear(2024)).thenReturn(List.of(game1, game2));

        //Then
        String expectedResponse = String.format(
                "[{\"id\":\"%s\",\"homeTeam\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\",\"imageUrl\":\"%s\",\"username\":\"\",\"isHuman\":%s},\"awayTeam\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\",\"imageUrl\":\"%s\",\"username\":\"\",\"isHuman\":%s},\"date\":\"%s\",\"homeScore\":%s,\"awayScore\":%s,\"homeTeamRank\":0,\"awayTeamRank\":0,\"schedule\":{\"id\":\"%s\",\"year\":%s}},{\"id\":\"%s\",\"homeTeam\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\",\"imageUrl\":\"%s\",\"username\":\"\",\"isHuman\":%s},\"awayTeam\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\",\"imageUrl\":\"%s\",\"username\":\"\",\"isHuman\":%s},\"date\":\"%s\",\"homeScore\":%s,\"awayScore\":%s,\"homeTeamRank\":0,\"awayTeamRank\":0,\"schedule\":{\"id\":\"%s\",\"year\":%s}}]",
                game1.id, team1.id, team1.name, team1.coach, team1.conference, team1.imageUrl, team1.isHuman, team2.id, team2.name, team2.coach, team2.conference, team2.imageUrl, team2.isHuman, game1.date, game1.homeScore, game1.awayScore, week.id, week.year,
                game2.id, team2.id, team2.name, team2.coach, team2.conference, team2.imageUrl, team2.isHuman, team1.id, team1.name, team1.coach, team1.conference, team1.imageUrl, team1.isHuman, game2.date, game2.homeScore, game2.awayScore, week.id, week.year
        );

        this.mockMvc.perform(get("/api/v1.0/schedule?year=2024"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
    @Test
    public void getScheduleYearIsOne_thenResultReturned() throws Exception {
        //Given
        Team team1 = createTeam("test 1", "coach 1", "conference 1", true, "image1");
        Team team2 = createTeam("test 2", "coach 2", "conference 2", false, "image2");

        Week week = createWeek(2024, 1);
        Game game1 = createGame(team1, team2, Date.valueOf("2024-01-01"), 0, 0, week);
        Game game2 = createGame(team2, team1, Date.valueOf("2024-01-02"), 0, 0, week);

        //When
        when(gameRepository.findGamesByYear(2024)).thenReturn(List.of(game1, game2));
        //Then
        String expectedResponse = "[]";

        this.mockMvc.perform(get("/api/v1.0/schedule?year=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
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
        testTeam.username = "";

        return testTeam;
    }

    private Game createGame(Team homeTeam, Team awayTeam, Date date, int homeScore, int awayScore, Week week) {
        Game testGame = new Game();
        UUID gameId = UUID.randomUUID();
        testGame.id = gameId;
        testGame.homeTeam = homeTeam;
        testGame.awayTeam = awayTeam;
        testGame.date = date;
        testGame.homeScore = homeScore;
        testGame.awayScore = awayScore;
        testGame.week = week;

        return testGame;
    }
    private Week createWeek(int year, int weekNumber) {
        Week testWeek = new Week();
        UUID weekId = UUID.randomUUID();
        testWeek.id = weekId;
        testWeek.year = year;
        testWeek.weekNumber = weekNumber;
        weekRepository.save(testWeek);
        return testWeek;
    }
}

