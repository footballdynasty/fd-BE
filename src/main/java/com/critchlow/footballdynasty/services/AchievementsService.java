package com.critchlow.footballdynasty.services;

import com.critchlow.footballdynasty.model.Achievements;
import com.critchlow.footballdynasty.repository.AchievementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AchievementsService {

    private final AchievementsRepository achievementsRepository;

    @Autowired
    public AchievementsService(AchievementsRepository achievementsRepository) {
        this.achievementsRepository = achievementsRepository;
    }

    public void deleteAchievement(UUID id) { achievementsRepository.delete(id); }
    public void updateAchievement(UUID id, String description, String reward, Long date_completed) { achievementsRepository.update(id, description, reward, date_completed); }
    public void createAchievements(Achievements me) { achievementsRepository.insert(me); }
    public List<Achievements> getAchievements() {
        return achievementsRepository.findAll();
    }
}
