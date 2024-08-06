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
        EntityGraph<?> graph = em.getEntityGraph("gameWithWeek");
        Game game = em.createQuery(findGamesQuery, Game.class)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .setParameter("gameId", gameId)
                .getSingleResult();
        return game;
    }

    @Override
    public List<Game> findUserTeams(int year) {
        ///select * from team t
        //join game g on t.id in (g.away_team_id, g.home_team_id)
        //join week w on g.week_id = w.id
        //where t.is_human
        //and w.week_number = 5
        String findUserTeams = """
                SELECT game From Game game
                join fetch Team team on team.id in (game.homeTeam.id, game.awayTeam.id)
                join fetch Week week on week.year = :year
                and team.isHuman
                """;
        EntityGraph<?> graph = em.getEntityGraph("gameWithWeek");
        List<Game> games = em.createQuery(findUserTeams, Game.class)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .setParameter("year", year)
                .getResultList();
        return games;
    }
}
