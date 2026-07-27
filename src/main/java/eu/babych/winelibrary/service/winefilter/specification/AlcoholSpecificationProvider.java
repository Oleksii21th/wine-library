package eu.babych.winelibrary.service.winefilter.specification;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.model.wine.Wine;
import eu.babych.winelibrary.service.winefilter.WineSpecificationProvider;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AlcoholSpecificationProvider implements WineSpecificationProvider<Wine> {
    @Override
    public String getKey() {
        return "alcohol";
    }

    @Override
    public Specification<Wine> getSpecification(WineFilterRequestDto dto) {
        if (dto.minAlcohol() == null && dto.maxAlcohol() == null) {
            return null;
        }

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (dto.minAlcohol() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("alcohol"), dto.minAlcohol()));
            }

            if (dto.maxAlcohol() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("alcohol"), dto.maxAlcohol()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
