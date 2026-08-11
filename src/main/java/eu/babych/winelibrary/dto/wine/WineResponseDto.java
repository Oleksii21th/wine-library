package eu.babych.winelibrary.dto.wine;

import eu.babych.winelibrary.model.wine.AgingType;
import eu.babych.winelibrary.model.wine.Food;
import eu.babych.winelibrary.model.wine.SugarType;
import eu.babych.winelibrary.model.wine.WineType;
import java.util.Set;

public record WineResponseDto(
        Long id,
        String name,
        String description,
        String vintage,
        Double alcohol,
        String volume,
        Long price,
        WineType wineType,
        SugarType sugarType,
        AgingType agingType,
        Set<Food> foods,
        String country,
        String region,
        String producer,
        Set<String> grapes,
        boolean favorite,
        String imageUrl) {
}
