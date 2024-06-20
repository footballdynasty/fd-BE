package com.critchlow.footballdynasty.controllers;

import com.critchlow.footballdynasty.model.Standings;
import com.critchlow.footballdynasty.services.StandingsService;
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
public class StandingsController {

    private StandingsService standingsService;

    @Autowired
    public StandingsController(StandingsService standingsService){
        this.standingsService = standingsService;
    }

    @GetMapping("/standings")
    public ResponseEntity<List<Standings>> getStandings(@RequestParam(required = false) Integer year){
        return new ResponseEntity<>(standingsService.getStandings(year), HttpStatus.OK);
    }
}
