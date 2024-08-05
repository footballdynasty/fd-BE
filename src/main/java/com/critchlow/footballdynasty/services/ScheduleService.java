package com.critchlow.footballdynasty.services;

import com.critchlow.footballdynasty.dtos.WinsLosses;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
            Integer largesWeekNumberForCurrentYear = weekRepository.findLargestWeekNumberByYear(LocalDateTime.now().getYear());
            List<Game> games = gameRepository.findGamesByYear(LocalDateTime.now().getYear());
            return games.stream().filter(g -> g.week.weekNumber == largesWeekNumberForCurrentYear).sorted(new GameComparator()).toList();
        }
        Integer largesWeekNumberForCurrentYear = weekRepository.findLargestWeekNumberByYear(year);
        List<Game> games =  gameRepository.findGamesByYear(year);
        return games.stream().filter(g -> g.week.weekNumber == largesWeekNumberForCurrentYear).sorted(new GameComparator()).toList();
    }

    @Transactional
    public Team getTeam(String name) {
        Team team = teamRepository.findTeamByName(name);
        return team;
    }

    @Transactional
    public Game createGame(String homeTeamName, String awayTeamName, String startDate, int year, int weekNumber) {
        return createGame(homeTeamName, awayTeamName, startDate, year, weekNumber, 0, 0);
    }

    @Transactional
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
        updateStandingsForUserTeams(year);
        return insertedGame;
    }

    @Transactional
    public void updateStandingsForUserTeams(int year){
        List<Game> games = gameRepository.findUserTeams(year);
        Map<Optional<Team>, List<Game>> mapUserTeams = games.stream()
                .collect(Collectors.groupingBy(Game::withUserCoach));
        mapUserTeams.forEach((k,v) -> {
            WinsLosses winsLosses = new WinsLosses();
            if(k.isEmpty()){
                return;
            }
            for(Game g : v){
                if(g.homeTeam.equals(k.get())){
                    if(g.homeScore > g.awayScore){
                        winsLosses.teamWins++;
                    } else if(g.awayScore > g.homeScore){
                        winsLosses.teamLosses++;
                    }
                } else {
                    if(g.awayScore > g.homeScore){
                        winsLosses.teamWins++;
                    } else if(g.awayScore < g.homeScore){
                        winsLosses.teamLosses++;
                    }
                }
            }

            saveStandings(k.get(), year, winsLosses);
        });
    }

    private void saveStandings(Team userTeam, int year,  WinsLosses winsLosses) {
        Standings userTeamStandings = standingsRepository.findByTeamNameAndYear(userTeam.name, year);
        userTeamStandings.wins = winsLosses.teamWins;
        userTeamStandings.losses = winsLosses.teamLosses;
        standingsRepository.save(userTeamStandings);
    }

    @Transactional
    public void updateGame(String gameId, int homeScore, int awayScore) {
        Game gameFound = gameRepository.findGameById(gameId);
        if (gameFound == null) {
            throw new EntityNotFoundException("Game with id " + gameId + " not found");
        }
        gameFound.homeScore = homeScore;
        gameFound.awayScore = awayScore;
        gameRepository.save(gameFound);
        updateStandingsForUserTeams(gameFound.week.year);
    }

//    public void deleteGame(UUID id) {
//        gameRepository.delete(id);
//    }
}
