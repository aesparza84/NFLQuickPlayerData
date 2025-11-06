package com.app.NFLPlayers.service;

import com.app.NFLPlayers.DTO.PlayerDTO;
import com.app.NFLPlayers.models.Player;
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

    public PlayerDTO getPlayerById(int id){
        Optional<Player> p = repo.findById(id);

        if (p.isPresent())
            return p.get().toDTO();

        return new PlayerDTO();
    }
}
