package com.app.NFLPlayers.utility;

import com.app.NFLPlayers.models.Team;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Expression;


public class TeamSpecs {

    public static Specification<Team> matchName(String name) {
        return (root, query, builder) -> {
            String pattern = "%"+name.toLowerCase().trim()+"%";

            Expression<String> anyName = builder.function(
                    "REPLACE",
                    String.class,
                    root.get("name"),
                    builder.literal(" "),
                    builder.literal("")
            );

          return builder.like(
                  builder.lower(anyName), pattern);
        };
    }

    public static Specification<Team> matchAbbreviation(String abbrev) {
        return (root, query, builder) -> {
            return builder.like(
                    builder.lower(root.get("abbreviation")), abbrev);
        };
    }
}
