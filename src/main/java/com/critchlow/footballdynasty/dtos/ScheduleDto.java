package com.critchlow.footballdynasty.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class ScheduleDto {
    public UUID id;
    public int year;
}
