package com.app.NFLPlayers.service;

import com.app.NFLPlayers.DTO.TeamDetailsDTO;
import com.app.NFLPlayers.DTO.TeamTagDTO;
import com.app.NFLPlayers.models.Player;
import com.app.NFLPlayers.models.Team;
import com.app.NFLPlayers.repository.TeamRepo;
import com.app.NFLPlayers.utility.GameLogSpecs;
import com.app.NFLPlayers.utility.TeamSpecs;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.Filter;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.spi.AbstractResourceBundleProvider;

//Specifications would be created here

@Service
public class TeamService {

    private TeamRepo repo;

    public TeamService(TeamRepo r){
        this.repo = r;
    }

    public List<TeamTagDTO> getAllTeams(){
        return repo.findAll().stream()
                .map(t -> t.ToTagDTO())
                .toList();
    }

    public List<TeamTagDTO> matchName(String name) {
        if (name == null){
            return new ArrayList<TeamTagDTO>();
        }

        Specification<Team> specs = TeamSpecs.matchName(name);

        return repo.findAll(specs).stream().map(t -> t.ToTagDTO()).toList();
    }

    public List<TeamTagDTO> matchAbbreviation(String abbrev) {
        if (abbrev == null){
            return new ArrayList<TeamTagDTO>();
        }

        return repo.findAllByAbbreviationContainsIgnoreCase(abbrev).stream()
                .map(t -> t.ToTagDTO())
                .toList();
    }

    public Optional<Team> getTeamById(int id){
        return repo.findById(id);
    }
}
