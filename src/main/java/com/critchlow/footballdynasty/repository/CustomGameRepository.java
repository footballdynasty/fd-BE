package com.critchlow.footballdynasty.repository;

import com.critchlow.footballdynasty.model.Game;

import java.util.List;

public interface CustomGameRepository {
    List<Game> findGames();
}
