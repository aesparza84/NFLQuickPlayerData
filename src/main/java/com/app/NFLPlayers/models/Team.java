package com.app.NFLPlayers.models;

import jakarta.persistence.*;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private int id;

    @Column(name = "team_name")
    private String name;

    @Column(name = "abbreviation")
    private String abbreviation;

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
}