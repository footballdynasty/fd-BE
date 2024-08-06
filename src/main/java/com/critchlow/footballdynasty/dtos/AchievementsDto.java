package com.critchlow.footballdynasty.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class AchievementsDto {
    public UUID id;
    public String description;
    public String reward;
    public Long date_completed;
}
