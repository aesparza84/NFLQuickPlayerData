package com.app.NFLPlayers.utility;

import com.app.NFLPlayers.models.Player;
import jakarta.persistence.criteria.Expression;
import org.springframework.data.jpa.domain.Specification;

public class PlayerSpecs {
    public static Specification<Player> MatchLikeName(String name) {
        return (root, query, criteriaBuilder) -> {
            String pattern = "%"+name.toLowerCase().trim()+"%";

            Expression<String> anyName = criteriaBuilder.function(
              "REPLACE",
              String.class,
              root.get("name"),
              criteriaBuilder.literal(" "),
              criteriaBuilder.literal("")
            );

            return criteriaBuilder.like(
                    criteriaBuilder.lower(anyName), pattern);
        };
    }

    public static Specification<Player> MatchLikePosition(String position) {
        return (root, query, criteriaBuilder) -> {
            String pattern = "%"+position.toLowerCase().trim()+"%";

            Expression<String> anyPosition = criteriaBuilder.function(
                    "REPLACE",
                    String.class,
                    root.get("position"),
                    criteriaBuilder.literal(" "),
                    criteriaBuilder.literal("")
            );

            return criteriaBuilder.like(
                    criteriaBuilder.lower(anyPosition), pattern);
        };
    }

    public static Specification<Player> MatchNumber(int number) {
        return (root, query, criteriaBuilder) -> {
          return criteriaBuilder.equal(root.get("number"),number);
        };
    }
}
