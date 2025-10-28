package com.app.NFLPlayers.repository;

import com.app.NFLPlayers.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {

}
