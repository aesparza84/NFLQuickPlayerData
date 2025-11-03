package com.app.NFLPlayers.service;

import com.app.NFLPlayers.DTO.TeamDetailsDTO;
import com.app.NFLPlayers.models.Player;
import com.app.NFLPlayers.models.Team;
import com.app.NFLPlayers.repository.TeamRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private TeamRepo repo;

    public TeamService(TeamRepo r){
        this.repo = r;
    }

    public List<TeamDetailsDTO> getAllTeams(){
        return repo.findAll().stream()
                .map(t -> t.ToDetailsDTO())
                .toList();
    }

    public Optional<Team> getTeamById(int id){
        return repo.findById(id);
    }
}
