package com.critchlow.footballdynasty.dtos;

import lombok.Data;

@Data
public class CreateGameDto {
	public String homeTeamName;
	public String awayTeamName;
	public String date;
	public int year;
	public int weekNumber;
	public Integer homeScore;
	public Integer awayScore;
}
