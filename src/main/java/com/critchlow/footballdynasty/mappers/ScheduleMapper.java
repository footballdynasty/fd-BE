package com.critchlow.footballdynasty.mappers;

import com.critchlow.footballdynasty.dtos.GameDto;
import com.critchlow.footballdynasty.dtos.ScheduleDto;
import com.critchlow.footballdynasty.model.Game;

import java.util.ArrayList;
import java.util.List;

public class ScheduleMapper {
    public List<GameDto> map(List<Game> games) {
        List<GameDto> gameDtos = new ArrayList<>();
        for (Game game : games) {
            GameDto gameDto = new GameDto();
            gameDto.id = game.id;
            gameDto.homeTeam = game.homeTeam;
            gameDto.awayTeam = game.awayTeam;
            gameDto.date = game.date;
            gameDto.homeScore = game.homeScore;
            gameDto.awayScore = game.awayScore;
            gameDto.homeTeamRank = game.homeTeamRank;
            gameDto.awayTeamRank = game.awayTeamRank;

            ScheduleDto scheduleDto = new ScheduleDto();
            scheduleDto.id = game.week.id;
            scheduleDto.year = game.week.year;
            gameDto.schedule = scheduleDto;
            gameDtos.add(gameDto);
        }
        return gameDtos;
    }
}
