package com.critchlow.footballdynasty.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class StandingsDto {
    public UUID id;
    public TeamDto team;
    public int year;
    public int wins;
    public int losses;
    public Integer rank;
    public Integer receiving_votes;
}
