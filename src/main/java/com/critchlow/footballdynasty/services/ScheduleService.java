package com.critchlow.footballdynasty.services;

import com.critchlow.footballdynasty.model.Game;
import com.critchlow.footballdynasty.model.Schedule;
import com.critchlow.footballdynasty.model.Team;
import com.critchlow.footballdynasty.repository.GameRepository;
import com.critchlow.footballdynasty.repository.ScheduleRepository;
import com.critchlow.footballdynasty.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduleService {
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final ScheduleRepository scheduleRepository;

    private static final String placeholderImage = "https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg";

    @Autowired
    public ScheduleService(GameRepository gameRepository, TeamRepository teamRepository, ScheduleRepository scheduleRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional(readOnly = true)
    public List<Game> getSchedule(Integer year) {
        if(year == null || year == 0){
            List<Game> games = gameRepository.findGames();
            return games;
        }
        List<Game> games =  gameRepository.findGames();
        return games.stream().filter(g -> g.schedule.year == year).toList();
    }

    @Transactional
    public Team getTeam(String name) {
        Team team = teamRepository.findTeamByName(name);
        return team;
    }

    public Game createGame(String homeTeamName, String awayTeamName, String startDate, int year) {
        Team homeTeam = teamRepository.findTeamByName(homeTeamName);
        Team awayTeam =  teamRepository.findTeamByName(awayTeamName);

        if (homeTeam == null) {
            homeTeam = new Team();
            homeTeam.name = homeTeamName;
            homeTeam.isHuman = false;
            homeTeam.imageUrl = placeholderImage;
            teamRepository.save(homeTeam);
        }

        if (awayTeam == null) {
            awayTeam = new Team();
            awayTeam.name = awayTeamName;
            awayTeam.isHuman = false;
            awayTeam.imageUrl = placeholderImage;
            teamRepository.save(awayTeam);
        }

        Schedule schedule = new Schedule();
        schedule.year = year;
        Schedule insertedSchedule = scheduleRepository.save(schedule);

        Game game = new Game();
        game.id = UUID.randomUUID();
        game.homeTeam = homeTeam;
        game.awayTeam = awayTeam;
        game.date = Date.valueOf(startDate);
        game.schedule = insertedSchedule;
        Game insertedGame = gameRepository.save(game);
        return insertedGame;
    }

//    public void updateGame(UUID id, int home_score, int away_score) {
//        gameRepository.update(id, home_score, away_score);
//    }
//    public void deleteGame(UUID id) {
//        gameRepository.delete(id);
//    }
}
