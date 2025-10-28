package com.app.NFLPlayers.service;

import com.app.NFLPlayers.models.Player;
import org.springframework.stereotype.Service;
import com.app.NFLPlayers.repository.PlayerRepo;

import java.util.List;

@Service
public class PlayerService {

    private PlayerRepo repo;

    public PlayerService(PlayerRepo r){
        this.repo = r;
    }

    public List<Player> getPlayers(){
        return repo.findAll();
    }

    public Player getPlayerById(int id){
        return null;
    }
}
