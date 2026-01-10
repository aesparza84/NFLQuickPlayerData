package com.app.NFLPlayers.DTO;

import com.app.NFLPlayers.models.GameLog;

import java.util.List;
import java.util.Set;

public class PlayerDTO {
    private String name;
    private int number;
    private String position;
    private TeamTagDTO team;
    private List<GameLogDTO> games;

    public PlayerDTO(){}
    public PlayerDTO(Set<GameLog> games, TeamTagDTO team, String position, int number, String name) {
        this.games = games.stream().map(g -> g.ToDTO()).toList();
        this.team = team;
        this.position = position;
        this.number = number;
        this.name = name;
    }

    public List<GameLogDTO> getGames() {
        return games;
    }

    public void setGames(Set<GameLog> games) {
        this.games = games.stream().map(g -> g.ToDTO()).toList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamTagDTO getTeam() {
        return team;
    }

    public void setTeam(TeamTagDTO team) {
        this.team = team;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
