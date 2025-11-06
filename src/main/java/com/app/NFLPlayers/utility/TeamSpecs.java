package com.app.NFLPlayers.utility;

import com.app.NFLPlayers.models.Team;
import org.springframework.data.jpa.domain.Specification;

public class TeamSpecs {

    public static Specification<Team> matchName(String name) {
        return (root, query, builder) -> {
          return builder.like(
                  builder.lower(root.get("name")), name);
        };
    }

    public static Specification<Team> matchAbbreviation(String abbrev) {
        return (root, query, builder) -> {
            return builder.like(
                    builder.lower(root.get("abbreviation")), abbrev);
        };
    }
}
