package eu.babych.winelibrary.service.winefilter.specification;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.model.wine.Wine;
import eu.babych.winelibrary.service.winefilter.WineSpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class FoodSpecificationProvider implements WineSpecificationProvider<Wine> {

    @Override
    public String getKey() {
        return "foods";
    }

    @Override
    public Specification<Wine> getSpecification(WineFilterRequestDto dto) {
        if (dto.foods() == null || dto.foods().isEmpty()) {
            return null;
        }

        return (root, query, cb) ->
                root.get("food").in(dto.foods());
    }
}
