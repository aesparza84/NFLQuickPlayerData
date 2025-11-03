package com.app.NFLPlayers.service;

import com.app.NFLPlayers.DTO.PlayerDTO;
import com.app.NFLPlayers.models.Player;
import org.springframework.stereotype.Service;
import com.app.NFLPlayers.repository.PlayerRepo;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepo repo;

    public PlayerService(PlayerRepo r){
        this.repo = r;
    }

    public List<PlayerDTO> getPlayers(){

        return repo.findAll().stream().map(s -> s.toDTO()).toList();
    }

    public PlayerDTO getPlayerById(int id){
        Optional<Player> p = repo.findById(id);

        if (p.isPresent())
            return p.get().toDTO();

        return new PlayerDTO();
    }
}
