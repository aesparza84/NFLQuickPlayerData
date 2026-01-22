package com.app.NFLPlayers.models;

import com.app.NFLPlayers.DTO.PlayerDTO;
import com.app.NFLPlayers.DTO.TeamDetailsDTO;
import com.app.NFLPlayers.DTO.TeamTagDTO;
import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="team_table")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private int id;

    @Column(name = "team_name")
    private String name;

    @Column(name = "abbreviation")
    private String abbreviation;

    //Checks for a 'team' field within Player
    @OneToMany(mappedBy = "team")
    private Set<Player> players;

    public Team(){}
    public Team(int id, String name, String abbreviation) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
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

    public Set<Player> getPlayers(){return players;}

    public int getId() {
        return id;
    }

    public TeamTagDTO ToTagDTO(){
        return new TeamTagDTO(
            this.id,
            this.name,
            this.abbreviation
        );
    }

    public TeamDetailsDTO ToDetailsDTO(){
        Set<PlayerDTO> set = players.stream()
                .map(p -> p.toDTO())
                .collect(Collectors.toSet());

        return new TeamDetailsDTO(
                this.id,
                this.name,
                this.abbreviation,
                set
        );
    }


}