package eu.babych.winelibrary.dto.wine;

import eu.babych.winelibrary.model.wine.AgingType;
import eu.babych.winelibrary.model.wine.SugarType;
import eu.babych.winelibrary.model.wine.WineType;
import java.math.BigDecimal;
import java.util.Set;

public record WineFilterRequestDto(
        WineType wineType,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        Long countryId,
        Long regionId,
        Set<Long> grapeIds,
        Integer vintage,
        SugarType sugarType,
        Double minAlcohol,
        Double maxAlcohol,
        Long producerId,
        Double volume,
        AgingType agingType,
        Boolean organic,
        Boolean alcoholFree,
        Boolean onSale,
        Boolean isNew,
        Boolean inStock) {
}
