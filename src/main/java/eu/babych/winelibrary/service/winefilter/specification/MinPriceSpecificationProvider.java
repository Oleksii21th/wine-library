package eu.babych.winelibrary.service.winefilter.specification;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.model.wine.Wine;
import eu.babych.winelibrary.service.winefilter.WineSpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class MinPriceSpecificationProvider implements WineSpecificationProvider<Wine> {
    @Override
    public String getKey() {
        return "minPrice";
    }

    @Override
    public Specification<Wine> getSpecification(WineFilterRequestDto dto) {
        if (dto.minPrice() == null) {
            return null;
        }

        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("price"), dto.minPrice());
    }
}