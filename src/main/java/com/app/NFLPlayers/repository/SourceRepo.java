package com.app.NFLPlayers.repository;

import com.app.NFLPlayers.models.GameLog;
import com.app.NFLPlayers.models.GameLogKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface SourceRepo extends JpaRepository<GameLog, GameLogKey> {

    @Modifying
    @Transactional
    @NativeQuery(value = "TRUNCATE TABLE football_main_table")
    void truncateMainTable();

}
