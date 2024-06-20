package com.critchlow.footballdynasty.mappers;

import com.critchlow.footballdynasty.dtos.AchievementsDto;
import com.critchlow.footballdynasty.model.Achievements;

import java.util.ArrayList;
import java.util.List;

public class AchievementsMapper {
    public List<AchievementsDto> map(List<Achievements> achievements) {
        List<AchievementsDto> achievementsDto = new ArrayList<>();
        for (Achievements achievement : achievements) {
            if (achievement != null) {
                AchievementsDto achievementDto = new AchievementsDto();
                achievementDto.id = achievement.id;
                achievementDto.description = achievement.description;
                achievementDto.reward = achievement.reward;
                achievementDto.date_completed = achievement.date_completed;
                achievementsDto.add(achievementDto);
            }
        }

        return achievementsDto;
    }
}
