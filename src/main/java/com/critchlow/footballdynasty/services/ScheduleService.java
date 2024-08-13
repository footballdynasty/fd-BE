package com.critchlow.footballdynasty.services;

import com.critchlow.footballdynasty.dtos.WinsLosses;
import com.critchlow.footballdynasty.model.*;
import com.critchlow.footballdynasty.repository.GameRepository;
import com.critchlow.footballdynasty.repository.StandingsRepository;
import com.critchlow.footballdynasty.repository.WeekRepository;
import com.critchlow.footballdynasty.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScheduleService {
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final WeekRepository weekRepository;
    private final StandingsRepository standingsRepository;

    private static final String PLACEHOLDER_IMAGE = "https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg";

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
            homeTeam.imageUrl = PLACEHOLDER_IMAGE;
            teamRepository.save(homeTeam);
        }

        if (awayTeam == null) {
            awayTeam = new Team();
            awayTeam.name = awayTeamName;
            awayTeam.isHuman = false;
            awayTeam.imageUrl = PLACEHOLDER_IMAGE;
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

    public void updateStandingsForUserTeams(int year){
        List<Team> userTeams = teamRepository.findUserTeams();
        userTeams.forEach(t -> {
            WinsLosses teamWinsLosses = getWinsLosses(t);
            saveStandings(t, year, teamWinsLosses);
        });
    }

    public static WinsLosses getWinsLosses(Team userTeam){
        WinsLosses winsLosses = new WinsLosses();
        if(userTeam.homeGames != null && !userTeam.homeGames.isEmpty()){
            for(Game g : userTeam.homeGames){
                if(g.homeScore > g.awayScore){
                    int teamWins = winsLosses.getTeamWins();
                    winsLosses.setTeamWins(teamWins + 1);
                } else if(g.awayScore > g.homeScore){
                    int teamLosses = winsLosses.getTeamLosses();
                    winsLosses.setTeamLosses(teamLosses + 1);
                }
            }
        }
        if(userTeam.awayGames != null && !userTeam.awayGames.isEmpty()) {
            for (Game g : userTeam.awayGames) {
                if (g.awayScore > g.homeScore) {
                    int teamWins = winsLosses.getTeamWins();
                    winsLosses.setTeamWins(teamWins + 1);
                } else if (g.awayScore < g.homeScore) {
                    int teamLosses = winsLosses.getTeamLosses();
                    winsLosses.setTeamLosses(teamLosses + 1);
                }
            }
        }
        winsLosses = getConferenceWinsLosses(userTeam, winsLosses);
        return winsLosses;
    }

    public static WinsLosses getConferenceWinsLosses(Team userTeam, WinsLosses winsLosses) {
        for(Game g : userTeam.homeGames){
            if(g.homeTeam.conference.equals(g.awayTeam.conference)) {
                if (g.homeScore > g.awayScore) {
                    int teamConferenceWins = winsLosses.getTeamConferenceWins();
                    winsLosses.setTeamConferenceWins(teamConferenceWins + 1);
                } else if (g.homeScore < g.awayScore) {
                    int teamConferenceLosses = winsLosses.getTeamConferenceLosses();
                    winsLosses.setTeamConferenceLosses(teamConferenceLosses + 1);
                }
            }
        }
        for(Game g : userTeam.awayGames){
            if(g.awayTeam.conference.equals(g.homeTeam.conference)) {
                if (g.awayScore > g.homeScore) {
                    int teamConferenceWins = winsLosses.getTeamConferenceWins();
                    winsLosses.setTeamConferenceWins(teamConferenceWins + 1);
                } else if (g.awayScore < g.homeScore) {
                    int teamConferenceLosses = winsLosses.getTeamConferenceLosses();
                    winsLosses.setTeamConferenceLosses(teamConferenceLosses + 1);
                }
            }
        }
        return winsLosses;
    }

    private void saveStandings(Team userTeam, int year,  WinsLosses winsLosses) {
        Standings userTeamStandings = standingsRepository.findByTeamNameAndYear(userTeam.name, year);
        userTeamStandings.wins = winsLosses.getTeamWins();
        userTeamStandings.losses = winsLosses.getTeamLosses();
        userTeamStandings.conference_wins = winsLosses.getTeamConferenceWins();
        userTeamStandings.conference_losses = winsLosses.getTeamConferenceLosses();
        standingsRepository.save(userTeamStandings);
    }

    public void updateGame(String homeTeamName, String awayTeamName, int year, int weekNumber,int homeScore,int awayScore) {
        String gameId = Game.createGameId(homeTeamName, awayTeamName, year, weekNumber);
        try {
            Game gameFound = gameRepository.findGameById(gameId);
            if(gameFound == null){
                throw new NoResultException("Game with id " + gameId + " not found");
            }
            gameFound.homeScore = homeScore;
            gameFound.awayScore = awayScore;
            gameRepository.save(gameFound);
            updateStandingsForUserTeams(gameFound.week.year);
        } catch (NoResultException e) {
            throw new NoResultException("Game with id " + gameId + " not found");
        }
    }

    public void updateGame(String gameId, int homeScore, int awayScore) {
        try {
            Game gameFound = gameRepository.findGameById(gameId);
            gameFound.homeScore = homeScore;
            gameFound.awayScore = awayScore;
            gameRepository.save(gameFound);
            updateStandingsForUserTeams(gameFound.week.year);
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Game with id " + gameId + " not found");
        }
    }

//    public void deleteGame(UUID id) {
//        gameRepository.delete(id);
//    }
}
