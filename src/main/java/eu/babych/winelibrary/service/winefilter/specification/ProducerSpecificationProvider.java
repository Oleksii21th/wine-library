package eu.babych.winelibrary.service.winefilter.specification;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.model.wine.Wine;
import eu.babych.winelibrary.service.winefilter.WineSpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProducerSpecificationProvider implements WineSpecificationProvider<Wine> {
    @Override
    public String getKey() {
        return "producerIds";
    }

    @Override
    public Specification<Wine> getSpecification(WineFilterRequestDto dto) {
        if (dto.producerIds() == null || dto.producerIds().isEmpty()) {
            return null;
        }

        return (root, query, cb) ->
                root.get("producer").get("id").in(dto.producerIds());
    }
}
