package com.app.NFLPlayers.models;

import com.app.NFLPlayers.DTO.PlayerDTO;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="player_table")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "player_id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id") //Creates a FK on team
    private Team team;

    @Column(name = "number")
    private int number;

    @Column(name = "position")
    private String position;

    @OneToMany(mappedBy = "player")
    private Set<GameLog> gameLogs = new HashSet<>();

    public Player(){}
    public Player(int id,String name, Team team, int number, String position) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.number = number;
        this.position = position;
    }

    public Set<GameLog> getGameLogs() {
        return gameLogs;
    }

    public void setGameLogs(Set<GameLog> gameLogs) {
        this.gameLogs = gameLogs;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
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

    public void setId(int id) {
        this.id = id;
    }

    public PlayerDTO toDTO() {
        return new PlayerDTO(
            this.gameLogs,
            this.team.ToTagDTO(),
            this.position,
            this.number,
            this.name
        );
    }

}