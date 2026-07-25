package eu.babych.winelibrary.service.winefilter.specification;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.model.wine.Wine;
import eu.babych.winelibrary.service.winefilter.WineSpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class GrapeSpecificationProvider implements WineSpecificationProvider<Wine> {
    @Override
    public String getKey() {
        return "grapes";
    }

    @Override
    public Specification<Wine> getSpecification(WineFilterRequestDto dto) {
        if (dto.grapeIds() == null || dto.grapeIds().isEmpty()) {
            return null;
        }

        return (root, query, cb) -> {
            if (query != null) {
                query.distinct(true);
            }

            return root.join("grapes")
                    .get("id")
                    .in(dto.grapeIds());
        };
    }
}
