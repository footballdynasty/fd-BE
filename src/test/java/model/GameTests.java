package model;

import com.critchlow.footballdynasty.model.Game;
import com.critchlow.footballdynasty.model.Team;
import com.critchlow.footballdynasty.model.Week;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTests {
	@Test
	void gameId_createsCorrectly(){

		String gameId = Game.createGameId("Bowling Green", "NC State", 2024, 9);
		assertEquals("BowlingGreenNCState20249", gameId);
	}

	@Test
	void equalsHashCodeContracts() {
		Team homeTeam = new Team();
		homeTeam.id = UUID.randomUUID();
		homeTeam.name = "home1";
		homeTeam.coach = "coach1";
		homeTeam.conference = "conference1";
		homeTeam.imageUrl = "imageUrl1";
		homeTeam.isHuman = true;
		homeTeam.username = "username1";

		Game game1 = new Game();
		game1.id = UUID.randomUUID();
		game1.date = Date.valueOf(LocalDate.now());
		game1.homeScore = 10;
		game1.awayScore = 7;
		game1.homeTeamRank = 1;
		game1.awayTeamRank = 2;
		game1.week = new Week();
		game1.week.year = 2024;
		game1.week.weekNumber = 1;
		game1.homeTeam = new Team();
		game1.homeTeam.id = UUID.randomUUID();
		game1.homeTeam.name = "home1";
		game1.awayTeam = new Team();
		game1.awayTeam.id = UUID.randomUUID();
		game1.awayTeam.name = "away1";
		game1.createGameId();

		Game game2 = new Game();
		game2.id = UUID.randomUUID();
		game2.date = Date.valueOf(LocalDate.now());
		game2.homeScore = 10;
		game2.awayScore = 7;
		game2.homeTeamRank = 1;
		game2.awayTeamRank = 2;
		game2.week = new Week();
		game2.week.year = 2024;
		game2.week.weekNumber = 1;
		game2.homeTeam = new Team();
		game2.homeTeam.id = UUID.randomUUID();
		game2.homeTeam.name = "home2";
		game2.awayTeam = new Team();
		game2.awayTeam.id = UUID.randomUUID();
		game2.awayTeam.name = "away2";
		game2.createGameId();
		homeTeam.homeGames = List.of(game1);
		homeTeam.awayGames = List.of(game2);

		Team awayTeam = new Team();
		awayTeam.id = UUID.randomUUID();
		awayTeam.name = "away1";
		awayTeam.coach = "coach2";
		awayTeam.conference = "conference1";
		awayTeam.imageUrl = "imageUrl1";
		awayTeam.isHuman = true;
		awayTeam.username = "username2";

		Game game3 = new Game();
		game3.id = UUID.randomUUID();
		game3.date = Date.valueOf(LocalDate.now());
		game3.homeScore = 17;
		game3.awayScore = 7;
		game3.homeTeamRank = 1;
		game3.awayTeamRank = 2;
		game3.week = new Week();
		game3.week.year = 2024;
		game3.week.weekNumber = 1;
		game3.homeTeam = new Team();
		game3.homeTeam.id = UUID.randomUUID();
		game3.homeTeam.name = "home3";
		game3.awayTeam = new Team();
		game3.awayTeam.id = UUID.randomUUID();
		game3.awayTeam.name = "away3";
		game3.createGameId();

		Game game4 = new Game();
		game4.id = UUID.randomUUID();
		game4.date = Date.valueOf(LocalDate.now());
		game4.homeScore = 21;
		game4.awayScore = 7;
		game4.homeTeamRank = 1;
		game4.awayTeamRank = 2;
		game4.week = new Week();
		game4.week.year = 2024;
		game4.week.weekNumber = 1;
		game4.homeTeam = new Team();
		game4.homeTeam.id = UUID.randomUUID();
		game4.homeTeam.name = "home4";
		game4.awayTeam = new Team();
		game4.awayTeam.id = UUID.randomUUID();
		game4.awayTeam.name = "away4";
		game4.createGameId();
		homeTeam.homeGames = List.of(game3);
		homeTeam.awayGames = List.of(game4);

		EqualsVerifier.forClass(Game.class)
				.withPrefabValues(Team.class, homeTeam, awayTeam)
				.verify();
	}
}
