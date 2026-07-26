package eu.babych.winelibrary.service.winefilter;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import org.springframework.data.jpa.domain.Specification;

public interface WineSearchSpecificationBuilder<T> {
    Specification<T> build(WineFilterRequestDto dto);
}
