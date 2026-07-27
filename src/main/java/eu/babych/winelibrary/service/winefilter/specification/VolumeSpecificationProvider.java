package eu.babych.winelibrary.service.winefilter.specification;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.model.wine.Wine;
import eu.babych.winelibrary.service.winefilter.WineSpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class VolumeSpecificationProvider implements WineSpecificationProvider<Wine> {
    @Override
    public String getKey() {
        return "volumes";
    }

    @Override
    public Specification<Wine> getSpecification(WineFilterRequestDto dto) {
        if (dto.volumes() == null || dto.volumes().isEmpty()) {
            return null;
        }

        return (root, query, cb) ->
                root.get("volume").in(dto.volumes());
    }
}
