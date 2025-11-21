package com.app.NFLPlayers.utility;

import com.app.NFLPlayers.models.GameLog;
import jakarta.persistence.criteria.Expression;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class GameLogSpecs {

    public static Specification<GameLog> MatchName(String name) {
        return (root, query, criteriaBuilder) -> {
            String pattern = "%"+name.toLowerCase().trim()+"%";

            //At Function: https://docs.oracle.com/javaee/7/api/javax/persistence/criteria/CriteriaBuilder.html

            Expression<String> anyName = criteriaBuilder.function(
                    "REPLACE",
                    String.class,
                    root.get("playerName"),
                    criteriaBuilder.literal(" "), criteriaBuilder.literal(""));

            return criteriaBuilder.like(
                    criteriaBuilder.lower(anyName), pattern);
        };
    }

    public static Specification<GameLog> MatchDate(LocalDate date) {
        return (root, query, criteriabuilder) -> {
            return criteriabuilder.equal(
                    root.get("date"),date);
        };
    }
}
