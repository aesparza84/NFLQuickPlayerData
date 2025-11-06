package com.app.NFLPlayers.service;

import com.app.NFLPlayers.DTO.TeamDetailsDTO;
import com.app.NFLPlayers.models.Player;
import com.app.NFLPlayers.models.Team;
import com.app.NFLPlayers.repository.TeamRepo;
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

    public List<TeamDetailsDTO> getAllTeams(){
        return repo.findAll().stream()
                .map(t -> t.ToDetailsDTO())
                .toList();
    }

    public List<TeamDetailsDTO> matchName(String name) {
        if (name == null){
            return new ArrayList<TeamDetailsDTO>();
        }

        return repo.findAllByNameContainsIgnoreCase(name).stream()
                .map(t -> t.ToDetailsDTO())
                .toList();

//        Specification<Team> spec = TeamSpecs.matchName(name);
//        return repo.findAll(spec).stream()
//                .map(t -> t.ToDetailsDTO())
//                .toList();
    }

    public List<TeamDetailsDTO> matchAbbreviation(String abbrev) {
        if (abbrev == null){
            return new ArrayList<TeamDetailsDTO>();
        }

        return repo.findAllByAbbreviationContainsIgnoreCase(abbrev).stream()
                .map(t -> t.ToDetailsDTO())
                .toList();

//        Specification<Team> spec = TeamSpecs.matchAbbreviation(abbrev);
//        return repo.findAll(spec).stream()
//                .map(t -> t.ToDetailsDTO())
//                .toList();
    }

    public Optional<Team> getTeamById(int id){
        return repo.findById(id);
    }
}
