package com.critchlow.footballdynasty.dtos;

import com.critchlow.footballdynasty.model.Team;

import java.sql.Date;
import java.util.UUID;

public class GameDto {
    public UUID id;
    public Team homeTeam;
    public Team awayTeam;
    public Date date;
    public int homeScore;
    public int awayScore;
    public ScheduleDto schedule;
}
