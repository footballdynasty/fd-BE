package com.critchlow.footballdynasty.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(name = "gameWithWeek",
                attributeNodes = {
                        @NamedAttributeNode("week"),
                        @NamedAttributeNode("homeTeam"),
                        @NamedAttributeNode("awayTeam")

                })
})
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public UUID id;
    public String gameId;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    public Team homeTeam;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    public Team awayTeam;
    @JoinColumn(name = "date")
    public Date date;
    @JoinColumn(name = "home_score")
    public int homeScore;
    @JoinColumn(name = "away_score")
    public int awayScore;
    public int homeTeamRank;
    public int awayTeamRank;
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "week_id")
    public Week week;

    public void createGameId(){
        String newGameId = homeTeam.name + awayTeam.name + week.year + week.weekNumber;
        this.gameId = newGameId.replaceAll("\\s+", "");
    }

    public static String createGameId(String homeTeamName, String awayTeamName, int year, int weekNumber){
        String gameId = homeTeamName + awayTeamName + year + weekNumber;
        return gameId.replaceAll("\\s+", "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Game game)) {
            return false;
        }
	    return homeScore == game.homeScore
                && awayScore == game.awayScore
                && homeTeamRank == game.homeTeamRank
                && awayTeamRank == game.awayTeamRank
                && Objects.equals(gameId, game.gameId)
                && Objects.equals(homeTeam, game.getHomeTeam())
                && Objects.equals(awayTeam, game.getAwayTeam())
                && Objects.equals(date, game.date)
                && Objects.equals(week, game.getWeek());
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, getHomeTeam(), getAwayTeam(), date, homeScore, awayScore, homeTeamRank, awayTeamRank, getWeek());
    }
}
