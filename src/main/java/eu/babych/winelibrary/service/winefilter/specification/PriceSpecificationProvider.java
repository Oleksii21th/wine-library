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
public class PriceSpecificationProvider implements WineSpecificationProvider<Wine> {

    @Override
    public String getKey() {
        return "price";
    }

    @Override
    public Specification<Wine> getSpecification(WineFilterRequestDto dto) {
        if (dto.minPrice() == null && dto.maxPrice() == null) {
            return null;
        }

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (dto.minPrice() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), dto.minPrice()));
            }

            if (dto.maxPrice() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), dto.maxPrice()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
