package com.app.NFLPlayers.controller;

import com.app.NFLPlayers.models.GameLog;
import com.app.NFLPlayers.models.Player;
import com.app.NFLPlayers.models.Team;
import com.app.NFLPlayers.service.PlayerService;
import com.app.NFLPlayers.service.TeamService;
import com.app.NFLPlayers.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/home")
public class PlayerLogController {

    private PlayerService playerService;
    private TeamService teamService;

    public PlayerLogController(PlayerService ps, TeamService ts){
        this.playerService = ps;
        this.teamService = ts;
    }

    @GetMapping("/players")
    public ResponseEntity<ApiResponse<List<Player>>> AllPlayers() {
        List<Player> list = playerService.getPlayers();

        ApiResponse<List<Player>> s =new ApiResponse<List<Player>>("Success", "Default Message", list);

        return ResponseEntity.status(HttpStatus.OK).body(s);
    }

    @GetMapping("/teams")
    public ResponseEntity<ApiResponse<List<Team>>> AllTeams() {
        List<Team> list = teamService.getAllTeams();

        ApiResponse<List<Team>> s =new ApiResponse<List<Team>>("Success", "Default Message", list);

        return ResponseEntity.status(HttpStatus.OK).body(s);
    }

    @GetMapping("/player")
    @ResponseBody
    public List<Player> GetPlayers(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "team", required = false) String team,
                                @RequestParam(value = "number", required = false) Integer number,
                                @RequestParam(value = "position", required = false) String position){

        /*
        return players.stream()
                .filter(p -> name == null || p.getName().equalsIgnoreCase(name))
                .filter(p -> team == null || p.getTeam().equalsIgnoreCase(team))
                .filter(p -> number == null || p.getNumber() == number)
                .filter(p -> position == null || p.getPosition().equalsIgnoreCase(position))
                .toList();

                return "This would be a player search";
        */

        return null;
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


        /*
        return games.stream()
                .filter(g -> date == null || g.getDate() == date)
                .filter(g -> playerId == null || g.getPlayerId() == playerId)
                .filter(g -> teamId == null || g.getTeamId() == teamId)
                .filter(g -> opponent == null || teams.stream().anyMatch(t -> t.getAbbreviation().equalsIgnoreCase(opponent)))
                .filter(g -> passingYds == null || g.getPassingYds() == passingYds)
                .filter(g -> receivingYds == null || g.getReceivingYds() == receivingYds)
                .filter(g -> rushingYds == null || g.getRushingYds() == rushingYds).toList();
        */

        return null;
    }

}
