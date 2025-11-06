package com.app.NFLPlayers.repository;

import ch.qos.logback.core.spi.FilterReply;
import com.app.NFLPlayers.DTO.PlayerDTO;
import com.app.NFLPlayers.models.Player;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {

    List<Player> findAllByNameContainsIgnoreCase(String name);

    List<Player> findAllByNumber(int number);

    List<Player> findAllByPositionContainsIgnoreCase(String position);
}
