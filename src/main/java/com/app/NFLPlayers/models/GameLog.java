package com.app.NFLPlayers.models;

import com.app.NFLPlayers.DTO.GameLogDTO;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@IdClass(GameLogKey.class)
@Table(name = "football_main_table")
public class GameLog {
    @Id
    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "opp")
    private String opposition;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "passingyds")
    private double passingYds;

    @Column(name = "receivingyds")
    private double receivingYds;

    @Column(name = "rushingyds")
    private double rushingYds;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player;

    public GameLog() {}
    public GameLog(Player player, double rushingYds, double receivingYds, double passingYds, String opponent, String name, String team, LocalDate date) {
        this.player = player;
        this.rushingYds = rushingYds;
        this.receivingYds = receivingYds;
        this.passingYds = passingYds;
        this.opposition = opponent;
        this.name = name;
        this.teamName = team;
        this.date = date;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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

    public String getName() {
        return name;
    }

    public void setName(String playerId) {
        this.name = playerId;
    }

    public String getOpposition() {
        return opposition;
    }

    public void setOpposition(String opponent) {
        this.opposition = opponent;
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
                this.opposition,
                this.name,
                this.date);
    }
}
