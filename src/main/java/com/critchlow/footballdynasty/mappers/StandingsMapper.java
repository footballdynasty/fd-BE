package com.critchlow.footballdynasty.mappers;

import com.critchlow.footballdynasty.dtos.StandingsDto;
import com.critchlow.footballdynasty.dtos.TeamDto;
import com.critchlow.footballdynasty.model.Standings;

import java.util.ArrayList;
import java.util.List;

public class StandingsMapper {
    public List<StandingsDto> map(List<Standings> standings) {
        List<StandingsDto> standingsDtos = new ArrayList<>();
        for (Standings standing : standings) {
            StandingsDto standingsDto = new StandingsDto();
            standingsDto.id = standing.id;
            standingsDto.year = standing.year;
            standingsDto.wins = standing.wins;
            standingsDto.losses = standing.losses;
            standingsDto.conference_wins = standing.conference_wins;
            standingsDto.conference_losses = standing.conference_losses;
            standingsDto.rank = standing.rank;
            standingsDto.receiving_votes = standing.receiving_votes;
            TeamDto teamDto = new TeamDto();
            teamDto.id = standing.team.id;
            teamDto.name = standing.team.name;
            teamDto.coach = standing.team.coach;
            teamDto.conference = standing.team.conference;
            teamDto.imageUrl = standing.team.imageUrl;
            teamDto.username = standing.team.username;
            teamDto.isHuman = standing.team.isHuman;
            standingsDto.team = teamDto;
            standingsDtos.add(standingsDto);
        }
        return standingsDtos;
    }
}
