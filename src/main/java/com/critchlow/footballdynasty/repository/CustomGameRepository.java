package com.critchlow.footballdynasty.repository;

import com.critchlow.footballdynasty.model.Game;

import java.util.List;

public interface CustomGameRepository {
    List<Game> findGames();
    List<Game> findGamesByYear(int year);
    List<Game> findGamesByWeekAndYear(int weekNumber, int year);
    Game findGameById(String gameId);
}
