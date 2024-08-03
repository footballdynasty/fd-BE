package com.critchlow.footballdynasty.services;

import com.critchlow.footballdynasty.model.*;
import com.critchlow.footballdynasty.repository.GameRepository;
import com.critchlow.footballdynasty.repository.StandingsRepository;
import com.critchlow.footballdynasty.repository.WeekRepository;
import com.critchlow.footballdynasty.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private final WeekRepository weekRepository;
    private final StandingsRepository standingsRepository;

    private static final String placeholderImage = "https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg";

    @Autowired
    public ScheduleService(GameRepository gameRepository, TeamRepository teamRepository, WeekRepository weekRepository, StandingsRepository standingsRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.weekRepository = weekRepository;
        this.standingsRepository = standingsRepository;
    }

    @Transactional(readOnly = true)
    public List<Game> getSchedule(Integer year) {
        if(year == null || year == 0){
            List<Game> games = gameRepository.findGames();
            games.sort(new GameComparator());
            return games;
        }
        List<Game> games =  gameRepository.findGames();
        return games.stream().filter(g -> g.week.year == year).sorted(new GameComparator()).toList();
    }

    @Transactional
    public Team getTeam(String name) {
        Team team = teamRepository.findTeamByName(name);
        return team;
    }

    public Game createGame(String homeTeamName, String awayTeamName, String startDate, int year, int weekNumber) {
        return createGame(homeTeamName, awayTeamName, startDate, year, weekNumber, 0, 0);
    }

    public Game createGame(String homeTeamName, String awayTeamName, String startDate, int year, int weekNumber, int homeScore, int awayScore) {
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

        Week weekFound = weekRepository.findWeekByWeekNumber(weekNumber);
        if(weekFound == null){
            weekFound = new Week();
            weekFound.year = year;
            weekFound.weekNumber = weekNumber;
            weekRepository.save(weekFound);
        }

        Game game = new Game();
        game.id = UUID.randomUUID();
        game.homeTeam = homeTeam;
        game.awayTeam = awayTeam;
        game.date = Date.valueOf(startDate);
        game.week = weekFound;
        game.homeScore = homeScore;
        game.awayScore = awayScore;
        game.createGameId();
        Game insertedGame = gameRepository.save(game);
        updateStandings(insertedGame);
        return insertedGame;
    }

    private void updateStandings(Game insertedGame) {
        List<Game> games = gameRepository.findGamesByYear(insertedGame.week.year);
        List<Game> homeTeamGames = games.stream()
                .filter(g -> g.homeTeam == insertedGame.homeTeam)
                .toList();
        List<Game> awayTeamGames = games.stream()
                .filter(g -> g.awayTeam == insertedGame.awayTeam)
                .toList();

        int homeTeamWins = 0;
        int awayTeamWins = 0;
        int homeTeamLosses = 0;
        int awayTeamLosses = 0;

        for (Game game : homeTeamGames) {
            if (game.homeScore > game.awayScore) {
                homeTeamWins++;
            } else if (game.homeScore < game.awayScore) {
                homeTeamLosses++;
            }
        }
        for (Game game : awayTeamGames) {
            if (game.awayScore > game.homeScore) {
                awayTeamWins++;
            } else if (game.awayScore < game.homeScore) {
                awayTeamLosses++;
            }
        }

        Standings homeTeamStandings = standingsRepository.findByTeamNameAndYear(insertedGame.homeTeam.name, insertedGame.week.year);
        Standings awayTeamStandings = standingsRepository.findByTeamNameAndYear(insertedGame.awayTeam.name, insertedGame.week.year);
        if(homeTeamStandings != null){
            homeTeamStandings.wins = homeTeamWins;
            homeTeamStandings.losses = homeTeamLosses;
            standingsRepository.save(homeTeamStandings);
        }
        if(awayTeamStandings != null){
            awayTeamStandings.wins = awayTeamWins;
            awayTeamStandings.losses = awayTeamLosses;
            standingsRepository.save(awayTeamStandings);
        }
    }

    public void updateGame(String gameId, int homeScore, int awayScore) {
        Game gameFound = gameRepository.findGameById(gameId);
        if (gameFound == null) {
            throw new EntityNotFoundException("Game with id " + gameId + " not found");
        }
        gameFound.homeScore = homeScore;
        gameFound.awayScore = awayScore;
        gameRepository.save(gameFound);
        updateStandings(gameFound);
    }

//    public void deleteGame(UUID id) {
//        gameRepository.delete(id);
//    }
}
