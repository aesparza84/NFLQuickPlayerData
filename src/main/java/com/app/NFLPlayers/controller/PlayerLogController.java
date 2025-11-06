package com.app.NFLPlayers.controller;

import com.app.NFLPlayers.DTO.PlayerDTO;
import com.app.NFLPlayers.DTO.TeamDetailsDTO;
import com.app.NFLPlayers.models.Team;
import com.app.NFLPlayers.service.PlayerService;
import com.app.NFLPlayers.service.TeamService;
import com.app.NFLPlayers.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponse<List<PlayerDTO>>> GetPlayers(@RequestParam(value = "name", required = false) String name,
                                                                   @RequestParam(value = "number", required = false) Integer number,
                                                                   @RequestParam(value = "position", required = false) String position) {
        List<PlayerDTO> list = null;
        ApiResponse<List<PlayerDTO>> response = null;

        if (name != null){
            list = playerService.matchPlayersByName(name);
            response = new ApiResponse<List<PlayerDTO>>("Success", "Matched players on name", list);
        }
        else if (number != null){
            list = playerService.matchPlayersByNumber(number);
            response = new ApiResponse<List<PlayerDTO>>("Success", "Matched players on number", list);
        }
        else if (position != null){
            list = playerService.matchPlayersByPosition(position);
            response = new ApiResponse<List<PlayerDTO>>("Success", "Matched players on position", list);
        }

        if (list != null)
        {
            if (list.isEmpty())
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResponse<>("Unsuccessful search","No players found",null));

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        //If no Query-parameters
        list = playerService.getAllPlayers();
        response = new ApiResponse<List<PlayerDTO>>("Success", "Default Message", list);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //RequestParam - Filter through data
    //PathVariable - Identifies specific resource (id)

    @GetMapping("/teams")
    public ResponseEntity<ApiResponse<List<TeamDetailsDTO>>> GetTeams(@RequestParam(value = "teamName",required = false) String teamName,
                                                                      @RequestParam(value = "teamAbbreviation",required = false) String abbrev) {

        List<TeamDetailsDTO> list = null;
        ApiResponse<List<TeamDetailsDTO>> response = null;

        if (teamName != null) {
            list = teamService.matchName(teamName);
            response = new ApiResponse<List<TeamDetailsDTO>>("Success", "Matched on Name", list);
        }
        else if (abbrev != null){
            list = teamService.matchAbbreviation(abbrev);
            response = new ApiResponse<List<TeamDetailsDTO>>("Success", "Matched on Abbrev.", list);
        }

        if (response != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        //If there are no RequestParams
        list = teamService.getAllTeams();
        response = new ApiResponse<List<TeamDetailsDTO>>("Success", "Default Message", list);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<ApiResponse<TeamDetailsDTO>> GetTeamById(@PathVariable(name = "id") Integer id) {
        Optional<Team> team = teamService.getTeamById(id);

        if (team.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<TeamDetailsDTO>("NOT FOUND", "Team not found",null));

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new ApiResponse<TeamDetailsDTO>("Success", "Team found",team.get().ToDetailsDTO()));
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
