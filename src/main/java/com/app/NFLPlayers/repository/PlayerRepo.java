package com.app.NFLPlayers.repository;

import com.app.NFLPlayers.DTO.PlayerDTO;
import com.app.NFLPlayers.models.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {

    List<Player> findAllByNameContainsIgnoreCase(String name);

    List<Player> findAllByNumber(int number);

    List<Player> findAllByPositionContainsIgnoreCase(String position);

    //Pagination
    Page<Player> findAll(Pageable pageable);

    Page<Player> findAllByNameContainsIgnoreCase(Pageable pageable,String name);

    Page<Player> findAllByNumber(Pageable pageable, int number);

    Page<Player> findAllByPositionContainsIgnoreCase(Pageable pageable, String position);
}
