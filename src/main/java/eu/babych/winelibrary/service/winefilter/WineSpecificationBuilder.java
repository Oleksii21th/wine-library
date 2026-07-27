package eu.babych.winelibrary.service.winefilter;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.dto.wine.WineSearchRequestDto;
import eu.babych.winelibrary.model.wine.Wine;
import java.util.Locale;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class WineSpecificationBuilder implements WineSearchSpecificationBuilder<Wine> {
    private final WineSpecificationProviderManager manager;

    public WineSpecificationBuilder(WineSpecificationProviderManager manager) {
        this.manager = manager;
    }

    @Override
    public Specification<Wine> buildFilter(WineFilterRequestDto dto) {

        Specification<Wine> spec = Specification.unrestricted();

        for (WineSpecificationProvider<Wine> provider : manager.getProviders()) {
            Specification<Wine> newSpec = provider.getSpecification(dto);

            if (newSpec != null) {
                spec = spec.and(newSpec);
            }
        }

        return spec;
    }

    @Override
    public Specification<Wine> buildSearch(WineSearchRequestDto dto) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + dto.name().toLowerCase() + "%");
    }
}
