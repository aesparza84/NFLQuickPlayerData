package com.app.NFLPlayers.service;

import com.app.NFLPlayers.DTO.TeamDetailsDTO;
import com.app.NFLPlayers.DTO.TeamTagDTO;
import com.app.NFLPlayers.models.Player;
import com.app.NFLPlayers.models.Team;
import com.app.NFLPlayers.repository.TeamRepo;
import com.app.NFLPlayers.utility.TeamSpecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private final Logger logger = LoggerFactory.getLogger(TeamService.class);

    public TeamService(TeamRepo r){
        this.repo = r;
    }

    public Page<TeamTagDTO> getAllTeams(int pageNum, int pageSize){

        //Instruction
        Pageable pageable = PageRequest.of(pageNum,pageSize);

        return repo.findAll(pageable).map(t -> t.ToTagDTO());
    }

    public Page<TeamTagDTO> matchName(int pageNum, int pageSize, String name) {

        //Instruction
        Pageable pageable = PageRequest.of(pageNum,pageSize);

        //Filter
        Specification<Team> specs = TeamSpecs.matchName(name);

        return repo.findAll(specs, pageable).map(t -> t.ToTagDTO());
    }

    public Page<TeamTagDTO> matchAbbreviation(int pageNum, int pageSize,String abbrev) {

        //Instruction
        Pageable page = PageRequest.of(pageNum, pageSize);

        //Filter
        Specification<Team> specs = TeamSpecs.matchAbbreviation(abbrev);

        return repo.findAll(specs, page).map(t->t.ToTagDTO());

    }

    public Page<TeamTagDTO> matchTeamSpecs(Specification<Team> specs, int pageNum, int pageSize) {
        Pageable page = PageRequest.of(pageNum, pageSize);
        logger.debug(">>> Created Team pageable {}",page);

        try {
            Page<TeamTagDTO> p =  repo.findAll(specs, page).map(t -> t.ToTagDTO());
            return p;
        } catch (Exception e) {
            logger.warn("Can not reach database: {}", e.getMessage());
        }

        return Page.empty();
    }

    public Optional<Team> getTeamById(int id){
        logger.info("Searching Team ID {}",id);

        try {
            Optional<Team> t =  repo.findById(id);
            return t;
        } catch (Exception e) {
            logger.warn("Can not reach database: {}",e.getMessage());
        }

        return Optional.empty();
    }
}
