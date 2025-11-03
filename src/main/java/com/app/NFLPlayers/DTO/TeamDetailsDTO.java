package com.app.NFLPlayers.DTO;

import java.util.Set;

public class TeamDetailsDTO {
    private int id;
    private String name;
    private String abbreviation;
    private Set<PlayerDTO> playerSet;

    public TeamDetailsDTO(){}
    public TeamDetailsDTO(int id, String name, String abbreviation, Set<PlayerDTO> playerSet) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.playerSet = playerSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Set<PlayerDTO> getPlayerSet() {
        return playerSet;
    }

    public void setPlayerSet(Set<PlayerDTO> playerSet) {
        this.playerSet = playerSet;
    }
}
