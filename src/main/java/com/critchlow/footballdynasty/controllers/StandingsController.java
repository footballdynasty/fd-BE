package com.critchlow.footballdynasty.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0")
public class StandingsController {


    @GetMapping("/standings")
    public String getStandings(){
        return "berni eats loud";
    }
}
