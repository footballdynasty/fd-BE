package com.critchlow.footballdynasty.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class TeamDto {
    public UUID id;
    public String name;
    public String coach;
    public String conference;
    public String imageUrl;
    public String username;
    @JsonProperty(value="isHuman")
    public boolean isHuman;
}
