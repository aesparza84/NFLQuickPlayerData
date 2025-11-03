package com.app.NFLPlayers.DTO;

public class PlayerDTO {
    private String name;
    private int number;
    private String position;
    private TeamTagDTO team;

    public PlayerDTO(){}
    public PlayerDTO(String name, TeamTagDTO team, int number, String position) {
        this.name = name;
        this.team = team;
        this.number = number;
        this.position = position;
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
