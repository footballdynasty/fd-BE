package services;

import com.critchlow.footballdynasty.dtos.StatisticDto;
import com.critchlow.footballdynasty.model.Game;
import com.critchlow.footballdynasty.model.Team;
import com.critchlow.footballdynasty.model.Week;
import com.critchlow.footballdynasty.repository.GameRepository;
import com.critchlow.footballdynasty.repository.WeekRepository;
import com.critchlow.footballdynasty.services.StatsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import java.sql.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatsServiceTests {
	private static final String PLACEHOLDER_IMAGE = "https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg";
	private StatsService statsService;

	@Mock
	private GameRepository gameRepository;
	@Mock
	private WeekRepository weekRepository;

	@BeforeEach
	public void setUp() {
		this.statsService = new StatsService(gameRepository, weekRepository);
	}

	@Test
	void calculateStats_forTeam_returnsCorrectly(){
		Team kyle = createTeam("Kyle-Jay Critchlow", "cloaked_revenant", "Bowling Green", true, "MAC");
		Team ilan = createTeam("Ilan Hurtado", "passionphruit", "Northern Illinois", true, "MAC");
		Week week = createWeek(2024, 1);
		Game game1 = createGame(kyle, ilan, 7, 33, week);

		// When
		StatisticDto kyleTeamStats = statsService.calculateTeamStatistics(kyle, game1);
		StatisticDto ilanTeamStats = statsService.calculateTeamStatistics(ilan, game1);
		// Then
		assertEquals(kyle.name, kyleTeamStats.teamName);
		assertEquals(kyle.coach, kyleTeamStats.coachName);
		assertEquals(kyle.username, kyleTeamStats.psn);
		assertEquals(game1.awayTeam.name, kyleTeamStats.opponent);
		assertEquals(game1.awayTeamRank, kyleTeamStats.opponentRank);
		assertEquals(game1.homeTeam.equals(kyle) ? "Home" : "Away", kyleTeamStats.locationPlayed);
		assertEquals(game1.homeScore, kyleTeamStats.pointsScored);
		assertEquals(game1.awayScore, kyleTeamStats.pointsAllowed);
		assertEquals(game1.homeScore > game1.awayScore ? "W" : "L", kyleTeamStats.result);
		assertEquals(-5.17, kyleTeamStats.teamStatistic);

		assertEquals(ilan.name, ilanTeamStats.teamName);
		assertEquals(ilan.coach, ilanTeamStats.coachName);
		assertEquals(ilan.username, ilanTeamStats.psn);
		assertEquals(game1.homeTeam.name, ilanTeamStats.opponent);
		assertEquals(game1.homeTeamRank, ilanTeamStats.opponentRank);
		assertEquals(game1.awayTeam.equals(ilan) ? "Away" : "Home", ilanTeamStats.locationPlayed);
		assertEquals(game1.awayScore, ilanTeamStats.pointsScored);
		assertEquals(game1.homeScore, ilanTeamStats.pointsAllowed);
		assertEquals(game1.awayScore > game1.homeScore ? "W" : "L", ilanTeamStats.result);
		assertEquals(30.16, ilanTeamStats.teamStatistic);
	}

	private static Week createWeek(int year, int weekNumber) {
		Week week = new Week();
		week.year = year;
		week.weekNumber = weekNumber;
		return week;
	}

	private static Team createTeam(String coachName, String username, String teamName, boolean isHuman, String conference) {
		Team team = new Team();
		team.coach = coachName;
		team.username = username;
		team.name = teamName;
		team.isHuman = isHuman;
		team.conference = conference;
		team.imageUrl = PLACEHOLDER_IMAGE;
		return team;
	}

	private static Game createGame(Team homeTeam, Team awayTeam, int homeScore, int awayScore, Week week) {
		Game game = new Game();
		game.id = UUID.randomUUID();
		game.homeTeam = homeTeam;
		game.awayTeam = awayTeam;
		game.date = Date.valueOf("2024-01-01");
		game.week = week;
		game.homeScore = homeScore;
		game.awayScore = awayScore;
		game.createGameId();
		return game;
	}
}
