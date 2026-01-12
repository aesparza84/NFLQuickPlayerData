package com.app.NFLPlayers.service;

import com.app.NFLPlayers.repository.SourceRepo;
import org.springframework.stereotype.Service;

@Service
public class MainDataService {

    private SourceRepo repo;

    public MainDataService(SourceRepo repo) {
        this.repo = repo;
    }

    public void ClearData() {
        repo.truncateMainTable();
        System.out.println("Truncated main source table");
    }
}
