package com.critchlow.footballdynasty.controllers;

import com.critchlow.footballdynasty.dtos.GameDto;
import com.critchlow.footballdynasty.mappers.ScheduleMapper;
import com.critchlow.footballdynasty.model.Game;
import com.critchlow.footballdynasty.model.Schedule;
import com.critchlow.footballdynasty.model.Team;
import com.critchlow.footballdynasty.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

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
    public HttpEntity<Object> createGame(@RequestParam String homeTeamName, @RequestParam String awayTeamName, @RequestParam String date, @RequestParam int year) {
        Team homeTeam = scheduleService.getTeam(homeTeamName);
        Team awayTeam =  scheduleService.getTeam(awayTeamName);

        if (homeTeam == null || awayTeam == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Game game = new Game();
        game.id = UUID.randomUUID();

        game.homeTeam = homeTeam;
        game.awayTeam = awayTeam;

        Schedule schedule = new Schedule();
        schedule.year = year;

        scheduleService.createGame( game.id, game.homeTeam, game.awayTeam, Date.valueOf(date), schedule);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

//    @PostMapping("/updateGame")
//    public HttpEntity<Object> updateGame(@RequestParam UUID id, @RequestParam int home_score, @RequestParam int away_score) {
//        scheduleService.updateGame(id, home_score, away_score);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/deleteGame")
//    public HttpEntity<Object> createAchievement(@RequestParam UUID id) {
//        scheduleService.deleteGame(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
