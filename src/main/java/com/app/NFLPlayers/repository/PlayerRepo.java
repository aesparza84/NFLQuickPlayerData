package com.app.NFLPlayers.repository;

import com.app.NFLPlayers.models.Player;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface PlayerRepo extends JpaRepository<Player, Integer>, JpaSpecificationExecutor<Player> {

    Player findByName(String name);

    @Modifying
    @Transactional
    @NativeQuery(value = "TRUNCATE TABLE player_table RESTART IDENTITY")
    void truncatePlayerTable();
}
