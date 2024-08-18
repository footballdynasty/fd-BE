package com.critchlow.footballdynasty.controllers;

import com.critchlow.footballdynasty.dtos.StatisticDto;
import com.critchlow.footballdynasty.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0")
public class StatsController {

	private StatsService statsService;

	@Autowired
	public StatsController(StatsService statsService) {
		this.statsService = statsService;
	}

	@GetMapping("/statistics")
	public ResponseEntity<Object> getStatistics(int weekNumber, int year) {
		List<StatisticDto> games = statsService.calculateStatistics(weekNumber, year);
		games.sort(Comparator.comparingDouble(StatisticDto::getTeamStatistic).reversed());
		return ResponseEntity.ok(games);
	}
}
