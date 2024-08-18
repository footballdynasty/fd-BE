package com.critchlow.footballdynasty.controllers;

import com.critchlow.footballdynasty.dtos.StatisticDto;
import com.critchlow.footballdynasty.dtos.StatisticsDto;
import com.critchlow.footballdynasty.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Object> getStatistics(@RequestParam(required = false) Integer year) {
		StatisticsDto statistics = statsService.calculateStatistics(year);
		statistics.statistics.sort(Comparator.comparingDouble(StatisticDto::getTeamStatistic).reversed());
		return ResponseEntity.ok(statistics);
	}
}
