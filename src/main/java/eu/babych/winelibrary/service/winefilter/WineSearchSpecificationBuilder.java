package eu.babych.winelibrary.service.winefilter;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.dto.wine.WineSearchRequestDto;
import org.springframework.data.jpa.domain.Specification;

public interface WineSearchSpecificationBuilder<T> {
    Specification<T> buildFilter(WineFilterRequestDto dto);

    Specification<T> buildSearch(WineSearchRequestDto dto);

}
