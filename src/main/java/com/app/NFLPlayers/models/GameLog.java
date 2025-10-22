package com.app.NFLPlayers.models;

import java.util.Date;

public class GameLog {
    private Date date;
    private int playerId;
    private String opponent;
    private double passingYds;
    private double receivingYds;
    private double rushingYds;

    public GameLog(double rushingYds, double receivingYds, double passingYds, String opponent, int playerId, Date date) {
        this.rushingYds = rushingYds;
        this.receivingYds = receivingYds;
        this.passingYds = passingYds;
        this.opponent = opponent;
        this.playerId = playerId;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
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

    public void setRushingYds(double rushingYds) {
        this.rushingYds = rushingYds;
    }
}
