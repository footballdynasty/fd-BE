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
        EntityGraph graph = em.getEntityGraph("gameWithWeek");
        List<Game> games = em.createQuery(findGamesQuery, Game.class)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .getResultList();
        return games;
    }

    @Override
    public List<Game> findGamesByYear(int year) {
        String findGamesQuery = """
                SELECT game FROM Game game where game.week.year = :year
                """;
        EntityGraph graph = em.getEntityGraph("gameWithWeek");
        List<Game> games = em.createQuery(findGamesQuery, Game.class)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .setParameter("year", year)
                .getResultList();
        return games;
    }

    @Override
    public Game findGameById(String gameId) {
        String findGamesQuery = """
                SELECT game FROM Game game where game.gameId=:gameId
                """;
        EntityGraph graph = em.getEntityGraph("gameWithWeek");
        Game game = em.createQuery(findGamesQuery, Game.class)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .setParameter("gameId", gameId)
                .getSingleResult();
        return game;
    }
}
