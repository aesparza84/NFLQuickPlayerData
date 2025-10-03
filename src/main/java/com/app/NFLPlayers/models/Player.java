package com.app.NFLPlayers.models;

public class Player {
    private int id;
    private String name;
    private String team;
    private int number;
    private String position;

    public Player(String name, String team, int number, String position) {
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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
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
