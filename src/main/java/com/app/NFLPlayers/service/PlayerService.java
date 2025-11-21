package com.app.NFLPlayers.service;

import com.app.NFLPlayers.DTO.PlayerDTO;
import com.app.NFLPlayers.models.Player;
import com.app.NFLPlayers.utility.CommonSpecs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.app.NFLPlayers.repository.PlayerRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepo repo;

    public PlayerService(PlayerRepo r){
        this.repo = r;
    }

    public List<PlayerDTO> getAllPlayers(){

        return repo.findAll().stream().map(s -> s.toDTO()).toList();
    }

    public List<PlayerDTO> matchPlayersByName(String name){
        if (name == null)
            return new ArrayList<>();

        Specification<PlayerDTO> specs =
                CommonSpecs.MatchName("James","name");

        return repo.findAllByNameContainsIgnoreCase(name).stream()
                .map(p -> p.toDTO())
                .toList();
    }

    public List<PlayerDTO> matchPlayersByNumber(Integer number){
        if (number == null)
            return new ArrayList<>();

        return repo.findAllByNumber(number).stream()
                .map(p -> p.toDTO())
                .toList();
    }

    public List<PlayerDTO> matchPlayersByPosition(String position){
        if (position == null)
            return new ArrayList<>();

        return repo.findAllByPositionContainsIgnoreCase(position).stream()
                .map(p -> p.toDTO())
                .toList();
    }

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

        //Pass pageable to repo
        Page<Player> all = repo.findAll(page);

        //Filter via specs
        Specification<PlayerDTO> specs =
                CommonSpecs.MatchName(name,"name");

        return repo.findAllByNameContainsIgnoreCase(page, name)
                .map(p -> p.toDTO());
    }

    public Page<PlayerDTO> matchPlayersByNumberPaged(int pageNum, int pageSize, Integer number){
        if (number == null)
            return null;

        //Instruction
        Pageable page = PageRequest.of(pageNum,pageSize);

        return repo.findAllByNumber(page, number)
                .map(p -> p.toDTO());
    }

    public Page<PlayerDTO> matchPlayersByPositionPaged(int pageNum, int pageSize, String position){
        if (position == null)
            return null;

        //Instruction
        Pageable page = PageRequest.of(pageNum,pageSize);

        return repo.findAllByPositionContainsIgnoreCase(page, position)
                .map(p -> p.toDTO());
    }
    //</editor-fold>

    public PlayerDTO getPlayerById(int id){
        Optional<Player> p = repo.findById(id);

        if (p.isPresent())
            return p.get().toDTO();

        return new PlayerDTO();
    }

}
