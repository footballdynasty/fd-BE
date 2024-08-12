package model;

import com.critchlow.footballdynasty.model.Game;
import com.critchlow.footballdynasty.model.Team;
import com.critchlow.footballdynasty.model.Week;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

public class TeamTests {

	@Test
	void equalsHashCodeContracts() {
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

		EqualsVerifier.forClass(Team.class)
				.withPrefabValues(Game.class, game1, game2)
				.verify();
	}
}
