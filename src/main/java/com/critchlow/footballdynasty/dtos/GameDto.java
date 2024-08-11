package com.critchlow.footballdynasty.dtos;

import com.critchlow.footballdynasty.model.Team;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
public class GameDto {
    public UUID id;
    public Team homeTeam;
    public Team awayTeam;
    public Date date;
    public int homeScore;
    public int awayScore;
    public int homeTeamRank;
    public int awayTeamRank;
    public ScheduleDto schedule;
}
