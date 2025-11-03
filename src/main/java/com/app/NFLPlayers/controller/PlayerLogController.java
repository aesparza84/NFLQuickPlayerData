package com.app.NFLPlayers.controller;

import com.app.NFLPlayers.DTO.PlayerDTO;
import com.app.NFLPlayers.DTO.TeamDetailsDTO;
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
import java.util.Optional;

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
    public ResponseEntity<ApiResponse<List<PlayerDTO>>> AllPlayers() {
        List<PlayerDTO> list = playerService.getPlayers();

        ApiResponse<List<PlayerDTO>> s =new ApiResponse<List<PlayerDTO>>("Success", "Default Message", list);

        return ResponseEntity.status(HttpStatus.OK).body(s);
    }

    @GetMapping("/teams")
    public ResponseEntity<ApiResponse<List<TeamDetailsDTO>>> AllTeams() {
        List<TeamDetailsDTO> list = teamService.getAllTeams();

        ApiResponse<List<TeamDetailsDTO>> s =
                new ApiResponse<List<TeamDetailsDTO>>("Success", "Default Message", list);

        return ResponseEntity.status(HttpStatus.OK).body(s);
    }

    @GetMapping("/team")
    public ResponseEntity<ApiResponse<Team>> GetTeam(@RequestParam(value = "id", required = true) Integer id) {
        Optional<Team> team = teamService.getTeamById(id);

        if (team.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<Team>("NOT FOUND", "Team not found",null));

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new ApiResponse<Team>("Success", "Team found",team.get()));
    }



    /*
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

                return "This would be a player search";

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


        return null;
    }

    */

}
