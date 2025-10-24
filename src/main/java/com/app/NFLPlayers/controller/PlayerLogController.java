package com.app.NFLPlayers.controller;

import com.app.NFLPlayers.models.GameLog;
import com.app.NFLPlayers.models.Player;
import com.app.NFLPlayers.models.Team;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/home")
public class PlayerLogController {

    private List<Player> players;
    private List<GameLog> games;
    private List<Team> teams;

    public PlayerLogController() {
        players = new ArrayList<>();
        teams = new ArrayList<>();
        games = new ArrayList<>();

        players.add(new Player(1,"John","Jaguars",49,"QB"));
        players.add(new Player(2,"Clyde","Panthers",2,"WR"));
        players.add(new Player(3,"Xavier","Elephants",13,"WR"));
        players.add(new Player(4, "Xao","Panthers",2,"RB"));
        players.add(new Player(5,"Jacob","Panthers",49,"RB"));

        teams.add(new Team(1,"Jaguars", "JG"));
        teams.add(new Team(2,"Elephants", "EH"));
        teams.add(new Team(3,"Panthers", "PA"));

        games.add(new GameLog(
                53.2,
                0,
                0,
                "JG",
                5,
                6,
                LocalDate.of(2025,9,1)));

        games.add(new GameLog(
                18,
                0,
                232,
                "EH",
                1,
                1,
                LocalDate.of(2025,9,1)));

        games.add(new GameLog(
                0,
                154,
                0,
                "PA",
                3,
                2,
                LocalDate.of(2025,9,7)));
    }

    @GetMapping
    public String Default() {
        return "Default response";
    }

    @GetMapping("/player")
    @ResponseBody
    public List<Player> GetPlayers(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "team", required = false) String team,
                                @RequestParam(value = "number", required = false) Integer number,
                                @RequestParam(value = "position", required = false) String position){

        return players.stream()
                .filter(p -> name == null || p.getName().equalsIgnoreCase(name))
                .filter(p -> team == null || p.getTeam().equalsIgnoreCase(team))
                .filter(p -> number == null || p.getNumber() == number)
                .filter(p -> position == null || p.getPosition().equalsIgnoreCase(position))
                .toList();
        //        return "This would be a player search";
    }

    @GetMapping("/gamelog")
    @ResponseBody
    public List<GameLog> GetGames(@RequestParam(value = "date",required = false) LocalDate date,
                               @RequestParam(value = "playerId",required = false) Integer playerId,
                               @RequestParam(value = "teamId",required = false) Integer teamId,
                               @RequestParam(value = "opponent",required = false) String opponent,
                               @RequestParam(value = "passingYds",required = false) Double passingYds,
                               @RequestParam(value = "receivingYds",required = false) Double receivingYds,
                               @RequestParam(value = "rushingYds",required = false) Double rushingYds) {

        return games.stream()
                .filter(g -> date == null || g.getDate() == date)
                .filter(g -> playerId == null || g.getPlayerId() == playerId)
                .filter(g -> teamId == null || g.getTeamId() == teamId)
                .filter(g -> opponent == null || teams.stream().anyMatch(t -> t.getAbbreviation().equalsIgnoreCase(opponent)))
                .filter(g -> passingYds == null || g.getPassingYds() == passingYds)
                .filter(g -> receivingYds == null || g.getReceivingYds() == receivingYds)
                .filter(g -> rushingYds == null || g.getRushingYds() == rushingYds).toList();
    }

}
