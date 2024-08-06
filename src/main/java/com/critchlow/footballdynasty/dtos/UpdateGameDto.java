package com.critchlow.footballdynasty.dtos;

import lombok.Data;

@Data
public class UpdateGameDto {
	public String homeTeamName;
	public String awayTeamName;
	public int year;
	public int weekNumber;
	public int homeScore;
	public int awayScore;
}
