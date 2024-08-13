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
		Team userTeam1 = createTeam(true, "HomeTeam", "conference1");
		Team userTeam2 = createTeam(true, "AwayTeam", "conference1");
		Team computer1 = createTeam(false, "Computer1", "conference2");
		Team computer2 = createTeam(false, "Computer2", "conference2");

		Week week = createWeek(2024, 1);
		Game game1 = createGame(userTeam1, userTeam2, 14, 7, week);
		Game game2 = createGame(computer1, userTeam1, 7, 14, week);
		Game game3 = createGame(userTeam1, computer2, 14, 7, week);

		userTeam1.homeGames.add(game1);
		userTeam1.awayGames.add(game2);
		userTeam1.homeGames.add(game3);


		// When
		WinsLosses winsLosses = ScheduleService.getWinsLosses(userTeam1);
		winsLosses = ScheduleService.getConferenceWinsLosses(userTeam2, winsLosses);

		// Then
		assertEquals(3, winsLosses.getTeamWins(), "Team1 should have 3 wins");
		assertEquals(0, winsLosses.getTeamLosses(), "Team1 should have 0 losses");
		assertEquals(1, winsLosses.getTeamConferenceWins(), "Team1 should have 1 conference wins");
		assertEquals(0, winsLosses.getTeamConferenceLosses(), "Team1 should have 0 conference losses");
	}

	private Week createWeek(int year, int weekNumber) {
		Week week = new Week();
		week.year = year;
		week.weekNumber = weekNumber;
		return week;
	}

	private Team createTeam(boolean isHuman, String name, String conference) {
		Team team = new Team();
		team.name = name;
		team.isHuman = isHuman;
		team.imageUrl = PLACEHOLDER_IMAGE;
		team.conference = conference;
		return team;
	}

	private Game createGame(Team homeTeam, Team awayTeam, int homeScore, int awayScore, Week week) {
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
