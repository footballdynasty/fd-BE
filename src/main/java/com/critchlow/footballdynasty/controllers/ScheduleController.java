package com.critchlow.footballdynasty.controllers;

import com.critchlow.footballdynasty.dtos.CreateGameDto;
import com.critchlow.footballdynasty.dtos.GameDto;
import com.critchlow.footballdynasty.dtos.UpdateGameDto;
import com.critchlow.footballdynasty.dtos.UpdateStandings;
import com.critchlow.footballdynasty.mappers.ScheduleMapper;
import com.critchlow.footballdynasty.model.Game;
import com.critchlow.footballdynasty.services.ScheduleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<GameDto>> getSchedule(@RequestParam(required = false) Integer year){
        List<Game> games = scheduleService.getSchedule(year);
        ScheduleMapper mapper = new ScheduleMapper();
        List<GameDto> gameDto = mapper.map(games);
        return new ResponseEntity<>(gameDto, HttpStatus.OK);
    }

    @PostMapping("/createGame")
    @Transactional
    public HttpEntity<Object> createGame(@RequestParam String homeTeamName, @RequestParam String awayTeamName, @RequestParam String date, @RequestParam int year, @RequestParam int weekNumber, @RequestParam(required = false) Integer homeScore, @RequestParam(required = false) Integer awayScore) {
        if(homeScore == null){
            homeScore = 0;
        }
        if(awayScore == null){
            awayScore = 0;
        }
        Game game = scheduleService.createGame(homeTeamName, awayTeamName, date, year, weekNumber, homeScore, awayScore);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PostMapping("/createGames")
    @Transactional
    public HttpEntity<Object> createGames(@RequestBody List<CreateGameDto> games) {
        for (CreateGameDto game : games) {
            if(game.homeScore == null){
                game.homeScore = 0;
            }
            if(game.awayScore == null){
                game.awayScore = 0;
            }

            scheduleService.createGame(game.homeTeamName, game.awayTeamName, game.date, game.year, game.weekNumber, game.homeScore, game.awayScore);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/updateGame")
    @Transactional
    public HttpEntity<Object> updateGame(@RequestParam String gameId, @RequestParam int homeScore, @RequestParam int awayScore) {
        try {
            scheduleService.updateGame(gameId, homeScore, awayScore);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/updateGames")
    @Transactional
    public HttpEntity<Object> updateGames(@RequestBody List<UpdateGameDto> games) {
        if(games == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(games.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(games.stream().anyMatch(g -> g.homeTeamName == null || g.awayTeamName == null || g.year == 0 || g.weekNumber == 0)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        for (UpdateGameDto game : games) {
            try {
                scheduleService.updateGame(game.homeTeamName, game.awayTeamName, game.year, game.weekNumber, game.homeScore, game.awayScore);
            } catch (EntityNotFoundException | NoResultException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/updateStandings")
    @Transactional
    public HttpEntity<?> updateStandings(@RequestBody UpdateStandings updateStandings){
        scheduleService.updateStandingsForUserTeams(updateStandings.year);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//
//    @DeleteMapping("/deleteGame")
//    public HttpEntity<Object> createAchievement(@RequestParam UUID id) {
//        scheduleService.deleteGame(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
