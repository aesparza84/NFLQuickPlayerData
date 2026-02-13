package com.app.NFLPlayers.service;

import com.app.NFLPlayers.DTO.PlayerDTO;
import com.app.NFLPlayers.models.Player;
import com.app.NFLPlayers.utility.PlayerSpecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.app.NFLPlayers.repository.PlayerRepo;

import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepo repo;

    public PlayerService(PlayerRepo r){
        this.repo = r;
    }

    public Boolean hasData(){
        return repo.count() > 1;
    }

    private final Logger logger = LoggerFactory.getLogger(PlayerService.class);

    //<editor-fold desc="Pagination">
    public Page<PlayerDTO> getAllPlayersPaged(int pageNum, int pageSize){
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        return repo.findAll(pageable).map(p -> p.toDTO());
    }

    public Page<PlayerDTO> matchPlayersByNamePaged(int pageNum, int pageSize, String name){
        if (name == null)
            return null;

        //Create the page instruction
        Pageable page = PageRequest.of(pageNum, pageSize);

        //Filter via specs
        Specification<Player> specs = PlayerSpecs.MatchLikeName(name);

        return repo.findAll(specs, page).map(p -> p.toDTO());
    }

    public Page<PlayerDTO> matchPlayersByNumberPaged(int pageNum, int pageSize, Integer number){
        if (number == null)
            return null;

        //Instruction
        Pageable page = PageRequest.of(pageNum,pageSize);

        //Filter
        Specification<Player> specs = PlayerSpecs.MatchNumber(number);

        return repo.findAll(specs, page)
                .map(p -> p.toDTO());
    }

    public Page<PlayerDTO> matchPlayersByPositionPaged(int pageNum, int pageSize, String position){
        if (position == null)
            return null;

        //Instruction
        Pageable page = PageRequest.of(pageNum,pageSize);

        //Filter
        Specification<Player> specs = PlayerSpecs.MatchLikePosition(position);

        return repo.findAll(specs, page)
                .map(p -> p.toDTO());
    }
    //</editor-fold>

    public Page<PlayerDTO> matchPlayerSpecs(Specification<Player> specs, int pageNum, int pageSize) {
        Pageable page = PageRequest.of(pageNum, pageSize);
        logger.debug(">>> Creating pageable: {}",page);
        logger.debug(">>> Searching Player repo");

        try {
            Page<PlayerDTO> res = repo.findAll(specs, page).map(p -> p.toDTO());
            return res;
        } catch (Exception e) {
            logger.warn("Can not reach database: {}", e.getMessage());
        }

        return Page.empty();
    }
}
