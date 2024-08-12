package com.critchlow.footballdynasty.repository;

import com.critchlow.footballdynasty.model.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class CustomTeamRepositoryImpl implements CustomTeamRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Team> findUserTeams() {
		String findUserTeams = """
                SELECT team FROM Team team where team.isHuman = true
                """;
		List<Team> userTeams = em.createQuery(findUserTeams, Team.class)
				.getResultList();

		return userTeams;
	}
}
