package eu.babych.winelibrary.service.winefilter;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.model.wine.Wine;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class WineSpecificationBuilder implements WineSearchSpecificationBuilder<Wine> {
    private final WineSpecificationProviderManager manager;

    public WineSpecificationBuilder(WineSpecificationProviderManager manager) {
        this.manager = manager;
    }


    @Override
    public Specification<Wine> build(WineFilterRequestDto dto) {

        Specification<Wine> spec = (root, query, cb) -> null;

        for (WineSpecificationProvider<Wine> provider : manager.getProviders()) {
            Specification<Wine> newSpec = provider.getSpecification(dto);

            if (newSpec != null) {
                spec = spec.and(newSpec);
            }
        }

        return spec;
    }
}
