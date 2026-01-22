package com.app.NFLPlayers.service;

import com.app.NFLPlayers.DTO.RawDataDTO;
import com.app.NFLPlayers.models.GameLog;
import com.app.NFLPlayers.models.Player;
import com.app.NFLPlayers.models.Team;
import com.app.NFLPlayers.repository.PlayerRepo;
import com.app.NFLPlayers.repository.SourceRepo;
import com.app.NFLPlayers.repository.TeamRepo;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

import java.io.*;

@Service
public class MainDataService {

    private SourceRepo mainRepo;
    private PlayerRepo playerRepo;
    private TeamRepo teamRepo;

    public MainDataService(SourceRepo mainRepo, PlayerRepo playerRepo, TeamRepo teamRepo) {
        this.mainRepo = mainRepo;
        this.playerRepo = playerRepo;
        this.teamRepo = teamRepo;
    }

    public void ClearData() {
        mainRepo.truncateMainTable();
        System.out.println("Truncated main source table");
    }

    public void CallScrape() {
        RestTemplate restTemplate = new RestTemplate();
        String scrapeURL = "http://localhost:8000/scrape";
        ObjectMapper mapper = new ObjectMapper();

        restTemplate.execute(
                scrapeURL,
                HttpMethod.GET,
                null,
                response -> {
                    if (!response.getStatusCode().is2xxSuccessful()) {
                        throw new IllegalStateException("Python API error: "+response.getStatusCode());
                    }

                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()))){
                        String line;
                        while ((line = reader.readLine()) != null) {
                            RawDataDTO playerDTO = mapper.readValue(line, RawDataDTO.class);

                            //repo save DTO
                            System.out.println(playerDTO);
                            Player player = SavePlayer(playerDTO);


                            GameLog gameLog = playerDTO.ToGameLog(player);
                            mainRepo.save(gameLog);
                            System.out.println("Saved: " + gameLog);
                        }
                    }
                    return null;
                }
        );
    }

    private Player SavePlayer(RawDataDTO dto) {
        Player player = playerRepo.findByName(dto.getName());

        if (player == null) {
            player = new Player();
            player.setName(dto.getName());

            Team team = teamRepo.findByName(dto.getTeam());
            System.out.println(team);
            player.setTeam(team);

            player.setNumber(dto.getNumber());
            player.setPosition(dto.getPosition());
            return playerRepo.save(player);
        }

        return player;
    }

}
