package com.app.NFLPlayers.utility;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Expression;
import org.springframework.data.jpa.domain.Specification;

public class CommonSpecs {
    public static <T> Specification<T> MatchName(String name, String prop){
        return (root, query, criteriaBuilder) -> {
            String pattern = "%"+name.toLowerCase().trim()+"%";

            Expression<String> anyName = criteriaBuilder.function(
                    "REPLACE",
                    String.class,
                    root.get(prop),
                    criteriaBuilder.literal(" "), criteriaBuilder.literal(""));

            return criteriaBuilder.like(
                    criteriaBuilder.lower(anyName), pattern );
        };
    }

}
