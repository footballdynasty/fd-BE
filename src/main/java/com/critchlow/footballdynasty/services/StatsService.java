package com.critchlow.footballdynasty.services;

import com.critchlow.footballdynasty.dtos.StatisticDto;
import com.critchlow.footballdynasty.model.Game;
import com.critchlow.footballdynasty.model.Team;
import com.critchlow.footballdynasty.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatsService {

	private final GameRepository gameRepository;

	@Autowired
	public StatsService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	public List<StatisticDto> calculateStatistics(int weekNumber, int year){
		List<Game> games = gameRepository.findGamesByWeekAndYear(weekNumber, year);
		List<StatisticDto> statistics = new ArrayList<>();
		for(Game game : games){
			StatisticDto homeTeam = calculateTeamStatistics(game.getHomeTeam(), game);
			StatisticDto awayTeam = calculateTeamStatistics(game.getAwayTeam(), game);
			statistics.add(homeTeam);
			statistics.add(awayTeam);
		}
		return statistics;
	}

	public StatisticDto calculateTeamStatistics(Team team, Game game) {
		double teamStatistic = getTeamStatistic(team, game);
		StatisticDto statisticDto = new StatisticDto();
		statisticDto.teamName = team.name;
		statisticDto.coachName = team.coach;
		statisticDto.psn = team.username;
		statisticDto.opponent = game.homeTeam.equals(team) ? game.awayTeam.name : game.homeTeam.name;
		statisticDto.opponentRank = game.homeTeam.equals(team) ? game.awayTeamRank : game.homeTeamRank;
		statisticDto.locationPlayed = game.homeTeam.equals(team) ? "Home" : "Away";
		statisticDto.pointsScored = game.homeTeam.equals(team) ? game.homeScore : game.awayScore;
		statisticDto.pointsAllowed = game.homeTeam.equals(team) ? game.awayScore : game.homeScore;
		if (game.homeTeam.equals(team)) {
			statisticDto.result = game.homeScore > game.awayScore ? "W" : "L";
		} else {
			statisticDto.result = game.awayScore > game.homeScore ? "W" : "L";
		}
		statisticDto.teamStatistic = Math.floor(teamStatistic * 100) / 100;
		return statisticDto;
	}

	private static double getTeamStatistic(Team team, Game game) {
		double teamStatistic = 0;
		double modifier = getWeightedModifier(team, game);
		if(game.homeTeam.equals(team)){
			teamStatistic = ((game.awayTeamRank * 5.00) + ((game.homeScore - game.awayScore) * 7.00) / 12.00) + modifier;
		} else {
			teamStatistic = ((game.homeTeamRank * 5.00) + ((game.awayScore - game.homeScore) * 7.00) / 12.00) + modifier;
		}
		return teamStatistic;
	}

	private static double getWeightedModifier(Team team, Game game) {
		if(game.homeTeam.equals(team) && game.awayTeam.isHuman){
			return 10.00;
		} else if(game.homeTeam.equals(team) && !game.awayTeam.isHuman){
			return 15.00;
		} else if(game.awayTeam.equals(team) && game.homeTeam.isHuman){
			return 15.00;
		} else {
			return 20.00;
		}
	}
}
