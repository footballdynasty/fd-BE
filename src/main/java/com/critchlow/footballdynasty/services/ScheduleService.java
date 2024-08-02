package com.critchlow.footballdynasty.services;

import com.critchlow.footballdynasty.model.Game;
import com.critchlow.footballdynasty.model.Schedule;
import com.critchlow.footballdynasty.model.Team;
import com.critchlow.footballdynasty.repository.GameRepository;
import com.critchlow.footballdynasty.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduleService {
    private final GameRepository gameRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(GameRepository gameRepository, ScheduleRepository scheduleRepository) {
        this.gameRepository = gameRepository;
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
        Team team = scheduleRepository.findTeamByName(name);
        return team;
    }

    public void createGame(UUID id, Team homeTeam, Team awayTeam, Date date, Schedule schedule) {
        Game game = new Game();
        game.id = id;
        game.homeTeam = homeTeam;
        game.awayTeam = awayTeam;
        game.date = date;
        game.schedule = schedule;
        gameRepository.insert(game);
    }
//    public void updateGame(UUID id, int home_score, int away_score) {
//        gameRepository.update(id, home_score, away_score);
//    }
//    public void deleteGame(UUID id) {
//        gameRepository.delete(id);
//    }
}
