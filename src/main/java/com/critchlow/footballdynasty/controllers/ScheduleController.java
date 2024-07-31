package com.critchlow.footballdynasty.controllers;

import com.critchlow.footballdynasty.dtos.GameDto;
import com.critchlow.footballdynasty.mappers.ScheduleMapper;
import com.critchlow.footballdynasty.model.Game;
import com.critchlow.footballdynasty.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
