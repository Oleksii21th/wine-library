package eu.babych.winelibrary.service.winefilter.specification;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.model.wine.Wine;
import eu.babych.winelibrary.service.winefilter.WineSpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RegionSpecificationProvider implements WineSpecificationProvider<Wine> {
    @Override
    public String getKey() {
        return "regionIds";
    }

    @Override
    public Specification<Wine> getSpecification(WineFilterRequestDto dto) {
        if (dto.regionIds() == null || dto.regionIds().isEmpty()) {
            return null;
        }

        return (root, query, cb) ->
                root.get("region").get("id").in(dto.regionIds());
    }
}
