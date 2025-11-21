package com.app.NFLPlayers.models;

import com.app.NFLPlayers.DTO.GameLogDTO;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@IdClass(GameLogId.class)
@Table(name = "football_main_table")
public class GameLog {
    @Id
    @Column(name = "date")
    private LocalDate date;

    @Id
    @Column(name = "name")
    private String playerName;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "opp")
    private String opponent;

    @Column(name = "passingyds")
    private double passingYds;

    @Column(name = "receivingyds")
    private double receivingYds;

    @Column(name = "rushingyds")
    private double rushingYds;

    public GameLog() {}
    public GameLog(double rushingYds, double receivingYds, double passingYds, String opponent, String playerId, String teamId, LocalDate date) {
        this.rushingYds = rushingYds;
        this.receivingYds = receivingYds;
        this.passingYds = passingYds;
        this.opponent = opponent;
        this.playerName = playerId;
        this.teamName = teamId;
        this.date = date;
    }

    public void setTeamName(String teamId) {
        this.teamName = teamId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerId) {
        this.playerName = playerId;
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

    public String getTeamName() {
        return teamName;
    }

    public GameLogDTO ToDTO(){
        return new GameLogDTO(
                this.rushingYds,
                this.receivingYds,
                this.passingYds,
                this.opponent,
                this.playerName = playerName,
                this.date);
    }
}
