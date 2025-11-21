package com.app.NFLPlayers.service;

import com.app.NFLPlayers.DTO.GameLogDTO;
import com.app.NFLPlayers.models.GameLog;
import com.app.NFLPlayers.repository.GameLogRepo;
import com.app.NFLPlayers.utility.GameLogSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GameLogService {
    private GameLogRepo repo;

    public GameLogService(GameLogRepo repo) {
        this.repo = repo;
    }

    public List<GameLogDTO> getMatchingGameLogs(String name) {
        Specification<GameLog> specs = GameLogSpecs.MatchName(name);

        return repo.findAll(specs).stream().map(g -> g.ToDTO()).toList();
    }
}