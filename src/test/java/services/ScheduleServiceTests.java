package services;

import com.critchlow.footballdynasty.dtos.WinsLosses;
import com.critchlow.footballdynasty.model.Game;
import com.critchlow.footballdynasty.model.Team;
import com.critchlow.footballdynasty.model.Week;
import com.critchlow.footballdynasty.repository.GameRepository;
import com.critchlow.footballdynasty.repository.StandingsRepository;
import com.critchlow.footballdynasty.repository.TeamRepository;
import com.critchlow.footballdynasty.repository.WeekRepository;
import com.critchlow.footballdynasty.services.ScheduleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTests {
	@Mock
	private GameRepository gameRepository;
    @Mock
	private TeamRepository teamRepository;
    @Mock
	private WeekRepository weekRepository;
    @Mock
	private StandingsRepository standingsRepository;
	private ScheduleService scheduleService;
	private static final String PLACEHOLDER_IMAGE = "https://upload.wikimedia.org/wikipedia/commons/6/6f/EA_Sports_monochrome_logo.svg";

	@BeforeEach
	public void setUp() {
		this.scheduleService = new ScheduleService(gameRepository, teamRepository, weekRepository, standingsRepository);
	}

	@Test
	void getWinsLosses_WithTwoUserTeams_ReturnsCorrectly(){
		// Given
		Game game1 = createGame(true, "Team1", 14, true, "AwayTeam1", 7, 1);
		Game game2 = createGame(true, "Team1", 7, false, "AwayTeam2", 14,2);
		Game game3 = createGame(false, "AwayTeam3", 27, true, "Team1", 14,3);
		Game game4 = createGame(true, "AwayTeam4", 10, true, "Team1", 14,4);
		List<Game> games = new ArrayList<>();
		games.add(game1);
		games.add(game2);
		games.add(game3);
		games.add(game4);

		// When
		WinsLosses winsLosses = ScheduleService.getWinsLosses(Optional.of(game1.homeTeam), games);

		// Then
		assertEquals(2, winsLosses.getTeamWins(), "Team1 should have 1 win");
		assertEquals(2, winsLosses.getTeamLosses(), "Team1 should have 1 loss");
	}

	private Game createGame(boolean homeTeamUser, String homeTeamName, int homeScore, boolean awayTeamUser, String awayTeamName, int awayScore, int weekNumber) {
		Team homeTeam = new Team();
		homeTeam.name = homeTeamName;
		homeTeam.isHuman = homeTeamUser;
		homeTeam.imageUrl = PLACEHOLDER_IMAGE;

		Team awayTeam = new Team();
		awayTeam.name = awayTeamName;
		awayTeam.isHuman = awayTeamUser;
		awayTeam.imageUrl = PLACEHOLDER_IMAGE;

		Week weekFound = new Week();
		weekFound.year = LocalDateTime.now().getYear();
		weekFound.weekNumber = weekNumber;

		Game game = new Game();
		game.id = UUID.randomUUID();
		game.homeTeam = homeTeam;
		game.awayTeam = awayTeam;
		game.date = Date.valueOf("2024-01-01");
		game.week = weekFound;
		game.homeScore = homeScore;
		game.awayScore = awayScore;
		game.createGameId();
		return game;
	}
}
