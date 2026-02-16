package com.app.NFLPlayers.service;

import com.app.NFLPlayers.DTO.RawDataDTO;
import com.app.NFLPlayers.models.GameLog;
import com.app.NFLPlayers.models.Player;
import com.app.NFLPlayers.models.Team;
import com.app.NFLPlayers.repository.PlayerRepo;
import com.app.NFLPlayers.repository.SourceRepo;
import com.app.NFLPlayers.repository.TeamRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

import java.io.*;

@Service
public class MainDataService {

    /// Bool to check whether scrape is vurrently scraping
    private boolean isRunning = false;

    private SourceRepo mainRepo;
    private PlayerRepo playerRepo;
    private TeamRepo teamRepo;

    private final Logger logger = LoggerFactory.getLogger(MainDataService.class);

    public MainDataService(SourceRepo mainRepo, PlayerRepo playerRepo, TeamRepo teamRepo) {
        this.mainRepo = mainRepo;
        this.playerRepo = playerRepo;
        this.teamRepo = teamRepo;
    }

    public void ClearTables() {
        if (isRunning) {
            logger.debug(">>> Scrape is currently running");
            return;
        }

        playerRepo.truncateCascadePlayerTable();
        logger.info(">>> Tables cleared");
    }

    public void CallScrape() {
        if (isRunning) {
            logger.debug(">>> Scrape currently running");
            return;
        }

        logger.debug(">>> Starting Scrape");
        isRunning = true;

        RestTemplate restTemplate = new RestTemplate();
//        String scrapeURL = "http://scraper:8000/scrape";
        String scrapeURL = "http://localhost:8000/scrape";
        ObjectMapper mapper = new ObjectMapper();

        logger.info(">>> Calling scrape FastAPI");
        try {
            restTemplate.execute(
                    scrapeURL,
                    HttpMethod.GET,
                    null,
                    response -> {
                        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()))){
                            String line;
                            while ((line = reader.readLine()) != null) {
                                RawDataDTO playerDTO = mapper.readValue(line, RawDataDTO.class);

                                //repo save DTO
                                logger.debug(">>> Converted player {}",String.valueOf(playerDTO));

                                Player player = SavePlayer(playerDTO);
                                logger.info(">>> Saved Player {}", player);


                                GameLog gameLog = playerDTO.ToGameLog(player);
                                mainRepo.save(gameLog);
                                logger.info(">>> Saved games: {}", gameLog);
                            }
                        } catch (Exception e) {
                            logger.warn(">>> Response Body not valid: {}", e.getMessage());
                        }

                        return null;
                    }
            );

        } catch (Exception e) {
            logger.error(">>> Could not reach FastAPI: {}",e.getMessage());
        }

        logger.debug(">>> Scrape stopped");
        isRunning = false;
    }

    private Player SavePlayer(RawDataDTO dto) {
        logger.debug(">>> Looking for Player {}",dto.getName());
        Player player = playerRepo.findByName(dto.getName());

        if (player == null) {
            logger.debug(">>> Creating new player {}",dto.getName());

            player = new Player();
            player.setName(dto.getName());

            Team team = teamRepo.findByName(dto.getTeam());
            player.setTeam(team);

            player.setNumber(dto.getNumber());
            player.setPosition(dto.getPosition());
            return playerRepo.save(player);
        }
        logger.debug(">>> Found player {}",dto.getName());
        return player;
    }

}
