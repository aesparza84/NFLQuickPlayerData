package com.app.NFLPlayers.service;

import com.app.NFLPlayers.models.Player;
import com.app.NFLPlayers.models.Team;
import com.app.NFLPlayers.repository.TeamRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private TeamRepo repo;

    public TeamService(TeamRepo r){
        this.repo = r;
    }

    public List<Team> getAllTeams(){
        return repo.findAll();
    }

    public List<Player> getTeamRoster(){
        return null;
    }
}
