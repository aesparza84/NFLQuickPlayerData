package com.app.NFLPlayers.DTO;

import java.time.LocalDate;

public class GameLogDTO {
    private LocalDate date;
    private String playerName;
    private String opponent;
    private double passingYds;
    private double receivingYds;
    private double rushingYds;

    public GameLogDTO() {}
    public GameLogDTO(double rushingYds, double receivingYds, double passingYds, String opponent, String playerName, LocalDate date) {
        this.rushingYds = rushingYds;
        this.receivingYds = receivingYds;
        this.passingYds = passingYds;
        this.opponent = opponent;
        this.playerName = playerName;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public double getPassingYds() {
        return passingYds;
    }

    public void setPassingYds(double passingYds) {
        this.passingYds = passingYds;
    }

    public double getReceivingYds() {
        return receivingYds;
    }

    public void setReceivingYds(double receivingYds) {
        this.receivingYds = receivingYds;
    }

    public double getRushingYds() {
        return rushingYds;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setRushingYds(double rushingYds) {
        this.rushingYds = rushingYds;
    }
}
