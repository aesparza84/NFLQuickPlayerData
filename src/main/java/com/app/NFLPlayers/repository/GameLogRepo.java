package com.app.NFLPlayers.repository;

import com.app.NFLPlayers.DTO.GameLogDTO;
import com.app.NFLPlayers.models.GameLog;
import com.app.NFLPlayers.models.GameLogId;
import com.app.NFLPlayers.models.Team;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface GameLogRepo extends JpaRepository<GameLog, GameLogId>, JpaSpecificationExecutor<GameLog> {

}
