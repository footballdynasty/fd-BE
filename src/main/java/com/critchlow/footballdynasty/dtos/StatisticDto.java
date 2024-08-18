package com.critchlow.footballdynasty.dtos;

import lombok.Data;

import java.util.Objects;

@Data
public final class StatisticDto {
	public String coachName;
	public String psn;
	public String teamName;
	public String opponent;
	public int opponentRank;
	public String locationPlayed;
	public int pointsScored;
	public int pointsAllowed;
	public String result;
	public double teamStatistic;
	public int teamRank;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof StatisticDto that)) {
			return false;
		}
		return getOpponentRank() == that.getOpponentRank()
				&& getPointsScored() == that.getPointsScored()
				&& getPointsAllowed() == that.getPointsAllowed()
				&& getTeamRank() == that.getTeamRank()
				&& Objects.equals(getCoachName(), that.getCoachName())
				&& Objects.equals(getPsn(), that.getPsn())
				&& Objects.equals(getTeamName(), that.getTeamName())
				&& Objects.equals(getOpponent(), that.getOpponent())
				&& Objects.equals(getLocationPlayed(), that.getLocationPlayed())
				&& Objects.equals(getResult(), that.getResult())
				&& Double.compare(that.getTeamStatistic(), getTeamStatistic()) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCoachName(), getPsn(), getTeamName(), getOpponent(), getOpponentRank(), getLocationPlayed(), getPointsScored(), getPointsAllowed(), getResult(), getTeamStatistic(), getTeamRank());
	}
}
