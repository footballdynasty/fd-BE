package com.critchlow.footballdynasty.controllers;

import com.critchlow.footballdynasty.model.Game;
import com.critchlow.footballdynasty.model.Schedule;
import com.critchlow.footballdynasty.model.Team;
import com.critchlow.footballdynasty.repository.GameRepository;
import com.critchlow.footballdynasty.services.ScheduleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ScheduleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    ScheduleService scheduleService;

    @MockBean
    GameRepository gameRepository;

    @Test
    public void getSchedule_thenResultReturned() throws Exception {
        //Given
        Team team1 = createTeam("test 1", "coach 1", "conference 1", true, "image1");
        Team team2 = createTeam("test 2", "coach 2", "conference 2", false, "image2");

        Schedule schedule = createSchedule(2024);
        Game game1 = createGame(team1, team2, Date.valueOf("2024-01-01"), 0, 0, schedule);
        Game game2 = createGame(team2, team1, Date.valueOf("2024-01-02"), 0, 0, schedule);

        //When
        when(gameRepository.findGames()).thenReturn(List.of(game1, game2));

        //Then
        String expectedResponse = String.format(
                "[{\"id\":\"%s\",\"homeTeam\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\",\"imageUrl\":\"%s\",\"isHuman\":%s},\"awayTeam\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\",\"imageUrl\":\"%s\",\"isHuman\":%s},\"date\":\"%s\",\"homeScore\":%s,\"awayScore\":%s,\"schedule\":{\"id\":\"%s\",\"year\":%s}},{\"id\":\"%s\",\"homeTeam\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\",\"imageUrl\":\"%s\",\"isHuman\":%s},\"awayTeam\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\",\"imageUrl\":\"%s\",\"isHuman\":%s},\"date\":\"%s\",\"homeScore\":%s,\"awayScore\":%s,\"schedule\":{\"id\":\"%s\",\"year\":%s}}]",
                game1.id, team1.id, team1.name, team1.coach, team1.conference, team1.imageUrl, team1.isHuman, team2.id, team2.name, team2.coach, team2.conference, team2.imageUrl, team2.isHuman, game1.date, game1.homeScore, game1.awayScore, schedule.id, schedule.year,
                game2.id, team2.id, team2.name, team2.coach, team2.conference, team2.imageUrl, team2.isHuman, team1.id, team1.name, team1.coach, team1.conference, team1.imageUrl, team1.isHuman, game2.date, game2.homeScore, game2.awayScore, schedule.id, schedule.year
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

        Schedule schedule = createSchedule(2024);
        Game game1 = createGame(team1, team2, Date.valueOf("2024-01-01"), 0, 0, schedule);
        Game game2 = createGame(team2, team1, Date.valueOf("2024-01-02"), 0, 0, schedule);

        //When
        when(gameRepository.findGames()).thenReturn(List.of(game1, game2));

        //Then
        String expectedResponse = String.format(
            "[{\"id\":\"%s\",\"homeTeam\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\",\"imageUrl\":\"%s\",\"isHuman\":%s},\"awayTeam\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\",\"imageUrl\":\"%s\",\"isHuman\":%s},\"date\":\"%s\",\"homeScore\":%s,\"awayScore\":%s,\"schedule\":{\"id\":\"%s\",\"year\":%s}},"
            + "{\"id\":\"%s\",\"homeTeam\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\",\"imageUrl\":\"%s\",\"isHuman\":%s},\"awayTeam\":{\"id\":\"%s\",\"name\":\"%s\",\"coach\":\"%s\",\"conference\":\"%s\",\"imageUrl\":\"%s\",\"isHuman\":%s},\"date\":\"%s\",\"homeScore\":%s,\"awayScore\":%s,\"schedule\":{\"id\":\"%s\",\"year\":%s}}]",
            game1.id, team1.id, team1.name, team1.coach, team1.conference, team1.imageUrl, team1.isHuman,
            team2.id, team2.name, team2.coach, team2.conference, team2.imageUrl, team2.isHuman,
            game1.date, game1.homeScore, game1.awayScore, schedule.id, schedule.year,
            game2.id, team2.id, team2.name, team2.coach, team2.conference, team2.imageUrl, team2.isHuman,
            team1.id, team1.name, team1.coach, team1.conference, team1.imageUrl, team1.isHuman,
            game2.date, game2.homeScore, game2.awayScore, schedule.id, schedule.year
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

        Schedule schedule = createSchedule(2024);
        Game game1 = createGame(team1, team2, Date.valueOf("2024-01-01"), 0, 0, schedule);
        Game game2 = createGame(team2, team1, Date.valueOf("2024-01-02"), 0, 0, schedule);

        //When
        when(gameRepository.findGames()).thenReturn(List.of(game1, game2));

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

        return testTeam;
    }

    private Game createGame(Team homeTeam, Team awayTeam, Date date, int homeScore, int awayScore, Schedule schedule) {
        Game testGame = new Game();
        UUID gameId = UUID.randomUUID();
        testGame.id = gameId;
        testGame.homeTeam = homeTeam;
        testGame.awayTeam = awayTeam;
        testGame.date = date;
        testGame.homeScore = homeScore;
        testGame.awayScore = awayScore;
        testGame.schedule = schedule;

        return testGame;
    }
    private Schedule createSchedule(int year) {
        Schedule testSchedule = new Schedule();
        UUID scheduleId = UUID.randomUUID();
        testSchedule.id = scheduleId;
        testSchedule.year = year;

        return testSchedule;
    }
}

