package com.critchlow.footballdynasty.controllers;

import com.critchlow.footballdynasty.dtos.AchievementsDto;
import com.critchlow.footballdynasty.mappers.AchievementsMapper;
import com.critchlow.footballdynasty.model.Achievements;
import com.critchlow.footballdynasty.services.AchievementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1.0")
public class AchievementsController {

    private AchievementsService achievementsService;

    @Autowired
    public AchievementsController(AchievementsService achievementsService){
        this.achievementsService = achievementsService;
    }

    @GetMapping("/achievements")
    public ResponseEntity<List<AchievementsDto>> getAchievements(){
        List<Achievements> achievements = achievementsService.getAchievements();
        AchievementsMapper mapper = new AchievementsMapper();
        List<AchievementsDto> achievementDto = mapper.map(achievements);
        return new ResponseEntity<>(achievementDto, HttpStatus.OK);
    }
    
    @PostMapping("/createAchievement")
    public HttpEntity<Object> createAchievement(@RequestParam String description, @RequestParam String reward) {
        Achievements achievements = new Achievements();
        achievements.id = UUID.randomUUID();
        achievements.description = description;
        achievements.reward = reward;

        achievementsService.createAchievements(achievements);
        return new ResponseEntity<>(achievements, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAchievement")
    public HttpEntity<Object> createAchievement(@RequestParam UUID id) {
        achievementsService.deleteAchievement(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
