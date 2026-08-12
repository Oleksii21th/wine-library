package eu.babych.winelibrary.dto.wine;

import eu.babych.winelibrary.model.wine.AgingType;
import eu.babych.winelibrary.model.wine.SugarType;
import eu.babych.winelibrary.model.wine.WineType;
import java.util.Set;

public record WineFilterRequestDto(
        Set<WineType> wineTypes,
        Set<Long> countryIds,
        Set<Long> regionIds,
        Set<Long> producerIds,
        Set<Long> grapeIds,
        Set<String> vintages,
        Set<SugarType> sugarTypes,
        Set<AgingType> agingTypes,
        Set<String> volumes,
        Set<String> foods,
        Double minAlcohol,
        Double maxAlcohol,
        Long minPrice,
        Long maxPrice) {
}
