package com.app.NFLPlayers.controller;

import com.app.NFLPlayers.DTO.GameLogDTO;
import com.app.NFLPlayers.DTO.PlayerDTO;
import com.app.NFLPlayers.DTO.TeamDetailsDTO;
import com.app.NFLPlayers.DTO.TeamTagDTO;
import com.app.NFLPlayers.models.Player;
import com.app.NFLPlayers.models.Team;
import com.app.NFLPlayers.service.MainDataService;
import com.app.NFLPlayers.service.PlayerService;
import com.app.NFLPlayers.service.TeamService;
import com.app.NFLPlayers.utility.PlayerSpecs;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class PlayerLogController {

    private PlayerService playerService;
    private TeamService teamService;
    private MainDataService sourceService;

    public PlayerLogController(PlayerService ps, TeamService ts, MainDataService ds){
        this.playerService = ps;
        this.teamService = ts;
        this.sourceService = ds;
    }

    @GetMapping("/reset")
    public String Reset(){
        playerService.ClearData();
        sourceService.ClearData();
        return "playerView";
    }

    @GetMapping("/scraper")
    public String GetScraperData(){
        sourceService.CallScrape();

        return "noDataView";
    }

    @GetMapping
    public String Home(){
        if (playerService.hasData())
            return "playerView";

        return "noDataView";
    }



    @GetMapping("/players")
    public String GetPlayers(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "number", required = false) Integer number,
                             @RequestParam(value = "position", required = false) String position,
                             @RequestParam(value = "size",defaultValue = "5",required = false) Integer size,
                             @RequestParam(value = "page",defaultValue = "0",required = false) Integer page,
                             HttpServletRequest request,
                             Model model) {

        Page<PlayerDTO> pageResult = null;
        PagedModel<PlayerDTO> pModel = null;

        //Url
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(request.getRequestURI());
        builder.query(request.getQueryString());


        //Base filter
        Specification<Player> spec = ((root, query, criteriaBuilder) -> null);

        if (name != null && !name.isEmpty()){
            spec = spec.and(PlayerSpecs.MatchLikeName(name));

            //pageResult = playerService.matchPlayersByNamePaged(page,size,name);
        }
        else if (number != null){
            spec = spec.and(PlayerSpecs.MatchNumber(number));

            //pageResult = playerService.matchPlayersByNumberPaged(page,size,number);
        }
        else if (position != null && !position.isEmpty()){
            spec = spec.and(PlayerSpecs.MatchLikePosition(position));

            //pageResult = playerService.matchPlayersByPositionPaged(page,size,position);
        }

        pageResult = playerService.matchPlayerSpecs(spec, page, size);
        List<PlayerDTO> s = pageResult.getContent();

        List<List<GameLogDTO>> logList = new ArrayList<>();


        if (pageResult.hasNext()) {
            builder.queryParamIfPresent("page", Optional.of(page+1));
            builder.replaceQueryParam("page", Optional.of(page+1));
            model.addAttribute("nextPage", builder.toUriString());
        }

        if (pageResult.hasPrevious()) {
            builder.queryParamIfPresent("page", Optional.of(page-1));
            builder.replaceQueryParam("page", Optional.of(page-1));
            model.addAttribute("prevPage", builder.toUriString());
        }

        pModel = new PagedModel<>(pageResult);
        model.addAttribute("playerPage", pModel);
        model.addAttribute("pageData", pageResult);
        return "playerView";

    }

    //RequestParam - Filter through data
    //PathVariable - Identifies specific resource (id)

    @GetMapping("/teams")
    public String GetTeams(@RequestParam(value = "teamName",required = false) String teamName,
                           @RequestParam(value = "teamAbbreviation",required = false) String abbrev,
                           @RequestParam(value = "size",defaultValue = "5",required = false) Integer size,
                           @RequestParam(value = "page",defaultValue = "0",required = false) Integer page,
                           Model model) {

        Page<TeamTagDTO> list = null;
        PagedModel<TeamTagDTO> pModel = null;

        if (teamName != null) {
//            list = teamService.matchName(teamName);
        }
        else if (abbrev != null){
//            list = teamService.matchAbbreviation(abbrev);
        }

        if (list != null && !list.isEmpty()){
            model.addAttribute("teamList", list);
            return "playerView";
        }

        //If there are no RequestParams
        list = teamService.getAllTeams(page, size);
        model.addAttribute("teamList", list);

        return "teamView";
    }

    @GetMapping("/team/{id}")
    public String GetTeamRoster(@PathVariable(name = "id") Integer id,
                                Model model) {
        Optional<Team> team = teamService.getTeamById(id);

        if (team.isEmpty())
            return null;

        //model add
        TeamDetailsDTO details = team.get().ToDetailsDTO();
        model.addAttribute("selectedTeam", details);
        return "teamView";
    }

    //API-RestMethods
    /*

    @GetMapping("/players") - paginated
    public ResponseEntity<ApiResponse<PagedModel<PlayerDTO>>> GetPlayers(@RequestParam(value = "name", required = false) String name,
                                                                         @RequestParam(value = "number", required = false) Integer number,
                                                                         @RequestParam(value = "position", required = false) String position,
                                                                         @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                                         @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        Page<PlayerDTO> pageResult = null;
        PagedModel<PlayerDTO> pModel = null;
        ApiResponse<PagedModel<PlayerDTO>> response = null;

        if (name != null){
            pageResult = playerService.matchPlayersByNamePaged(page,size,name);
        }
        else if (number != null){
            pageResult = playerService.matchPlayersByNumberPaged(page,size,number);
        }
        else if (position != null){
            pageResult = playerService.matchPlayersByPositionPaged(page,size,position);
        }

        if (pageResult != null)
        {
            if (pageResult.isEmpty())
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResponse<>("Unsuccessful search","No players found",null));

            pModel = new PagedModel<>(pageResult);
            response = new ApiResponse<>("Success", "Paged success", pModel);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        //If no Query-parameters
        pageResult = playerService.getAllPlayersPaged(page,size);
        pModel = new PagedModel<>(pageResult);
        response = new ApiResponse<>("Success", "Default Message", pModel);
        return ResponseEntity.status(HttpStatus.OK).body(response);
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

    @GetMapping("/gamelog")
    public ResponseEntity<ApiResponse<List<GameLogDTO>>> GetGameLog(@RequestParam(value = "name", required = true) String playerName) {
        ApiResponse<List<GameLogDTO>> response = null;

        List<GameLogDTO> list = gameLogService.getMatchingGameLogs(playerName);
        response = new ApiResponse<List<GameLogDTO>>("Default", "Default", list);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    */

}
