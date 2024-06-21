package com.critchlow.footballdynasty.dtos;

import com.critchlow.footballdynasty.model.Team;

import java.util.UUID;

public class StandingsDto {
    public UUID id;
    public TeamDto team;
    public int year;
    public int wins;
    public int losses;
    public Integer rank;
    public Integer receiving_votes;
}
