package com.critchlow.footballdynasty.mappers;

import com.critchlow.footballdynasty.dtos.GameDto;
import com.critchlow.footballdynasty.dtos.ScheduleDto;
import com.critchlow.footballdynasty.dtos.TeamDto;
import com.critchlow.footballdynasty.model.Game;

import java.util.ArrayList;
import java.util.List;

public class ScheduleMapper {
    public List<GameDto> map(List<Game> games) {
        List<GameDto> gameDtos = new ArrayList<>();
        for (Game game : games) {
            GameDto gameDto = new GameDto();
            gameDto.id = game.id;
            gameDto.date = game.date;
            gameDto.homeScore = game.homeScore;
            gameDto.awayScore = game.awayScore;
            gameDto.homeTeamRank = game.homeTeamRank;
            gameDto.awayTeamRank = game.awayTeamRank;

            TeamDto homeTeam = new TeamDto();
            homeTeam.id = game.homeTeam.id;
            homeTeam.name = game.homeTeam.name;
            homeTeam.coach = game.homeTeam.coach;
            homeTeam.conference = game.homeTeam.conference;
            homeTeam.imageUrl = game.homeTeam.imageUrl;
            homeTeam.isHuman = game.homeTeam.isHuman;
            homeTeam.username = game.homeTeam.username;
            gameDto.homeTeam = homeTeam;

            TeamDto awayTeam = new TeamDto();
            awayTeam.id = game.awayTeam.id;
            awayTeam.name = game.awayTeam.name;
            awayTeam.coach = game.awayTeam.coach;
            awayTeam.conference = game.awayTeam.conference;
            awayTeam.imageUrl = game.awayTeam.imageUrl;
            awayTeam.isHuman = game.awayTeam.isHuman;
            awayTeam.username = game.awayTeam.username;
            gameDto.awayTeam = awayTeam;

            ScheduleDto scheduleDto = new ScheduleDto();
            scheduleDto.id = game.week.id;
            scheduleDto.year = game.week.year;
            gameDto.schedule = scheduleDto;
            gameDtos.add(gameDto);
        }
        return gameDtos;
    }
}
