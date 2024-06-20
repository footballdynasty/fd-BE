package com.critchlow.footballdynasty.services;

import com.critchlow.footballdynasty.model.Achievements;
import com.critchlow.footballdynasty.repository.AchievementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementsService {

    private final AchievementsRepository achievementsRepository;

    @Autowired
    public AchievementsService(AchievementsRepository achievementsRepository) {
        this.achievementsRepository = achievementsRepository;
    }

    public void createAchievements(Achievements me) { achievementsRepository.insert(me); }
    public List<Achievements> getAchievements() {
        return achievementsRepository.findAll();
    }
}
