package com.app.NFLPlayers.repository;

import com.app.NFLPlayers.DTO.TeamDetailsDTO;
import com.app.NFLPlayers.models.Team;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/// For custom repos, Spring uses naming convention 'xxxImpl'
/// to locate and USE the implementation when it's called.
///
/// We created CustomTeamRepo -> CustomTeamRepoImpl (Overrides with implementation)
/// Then we do TeamRepo : CustomTeamRepo, and Spring connects to the class 'Impl'


@Repository
public interface TeamRepo extends JpaRepository<Team, Integer>, JpaSpecificationExecutor<Team>{


    List<Team> findAllByNameContainsIgnoreCase(String name);

    List<Team> findAllByAbbreviationContainsIgnoreCase(String abbreviation);
}
