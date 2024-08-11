package com.critchlow.footballdynasty.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class TeamDto {
    public UUID id;
    public String name;
    public String coach;
    public String conference;
}
