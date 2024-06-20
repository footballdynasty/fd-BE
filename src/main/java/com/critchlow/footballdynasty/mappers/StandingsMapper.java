package com.critchlow.footballdynasty.mappers;

import com.critchlow.footballdynasty.dtos.StandingsDto;
import com.critchlow.footballdynasty.model.Standings;

import java.util.ArrayList;
import java.util.List;

public class StandingsMapper {
    public List<StandingsDto> map(List<Standings> standings) {
        List<StandingsDto> standingsDtos = new ArrayList<>();
        for (Standings standing : standings) {
            StandingsDto standingsDto = new StandingsDto();
            standingsDto.id = standing.id;
            standingsDto.team = standing.team;
            standingsDto.year = standing.year;
            standingsDto.wins = standing.wins;
            standingsDto.losses = standing.losses;
            standingsDto.rank = standing.rank;
            standingsDto.receiving_votes = standing.receiving_votes;
            standingsDtos.add(standingsDto);
        }

        return standingsDtos;
    }
}
