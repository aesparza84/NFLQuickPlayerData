package com.app.NFLPlayers.DTO;

import com.app.NFLPlayers.models.GameLog;
import com.app.NFLPlayers.models.Player;

import java.time.LocalDate;

public class RawDataDTO {
    private String team;
    private String name;
    private Integer number;
    private String position;
    private String date;
    private String opponent;
    private Double passingYds;
    private Double receivingYds;
    private Double rushingYds;

    public RawDataDTO(String team, String name, Integer number, String position, String date, String opponent, Double passingYds, Double receivingYds, Double rushingYds) {
        this.team = team;
        this.name = name;
        this.number = number;
        this.position = position;
        this.date = date;
        this.opponent = opponent;
        this.passingYds = passingYds;
        this.receivingYds = receivingYds;
        this.rushingYds = rushingYds;
    }

    public Double getRushingYds() {
        return rushingYds;
    }

    public void setRushingYds(Double rushingYds) {
        this.rushingYds = rushingYds;
    }

    public Double getReceivingYds() {
        return receivingYds;
    }

    public void setReceivingYds(Double receivingYds) {
        this.receivingYds = receivingYds;
    }

    public Double getPassingYds() {
        return passingYds;
    }

    public void setPassingYds(Double passingYds) {
        this.passingYds = passingYds;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    @Override
    public String toString() {
        return "RawDataDTO{" +
                "Team='" + team + '\'' +
                ", Name='" + name + '\'' +
                ", Number=" + number +
                ", Position='" + position + '\'' +
                ", Date='" + date + '\'' +
                ", Opponent='" + opponent + '\'' +
                ", PassingYds=" + passingYds +
                ", ReceivingYds=" + receivingYds +
                ", RushingYds=" + rushingYds +
                '}';
    }

    public GameLog ToGameLog(Player player){
        return new GameLog(
               player,
               this.rushingYds,
                this.receivingYds,
                this.passingYds,
                this.opponent,
                this.name,
                this.team,
                LocalDate.parse(this.date),
                this.number,
                this.position
        );
    }
}
