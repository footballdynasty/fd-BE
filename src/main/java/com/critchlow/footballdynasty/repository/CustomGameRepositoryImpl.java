package com.critchlow.footballdynasty.repository;

import com.critchlow.footballdynasty.model.Game;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class CustomGameRepositoryImpl implements CustomGameRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Game> findGames() {
        String findGamesQuery = """
                SELECT game FROM Game game
                """;
        EntityGraph graph = em.getEntityGraph("gameWithSchedule");
        List<Game> games = em.createQuery(findGamesQuery, Game.class)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .getResultList();
        return games;
    }
}
