package eu.babych.winelibrary.dto.wine;

import java.math.BigDecimal;
import java.util.Set;

public record WineResponseDto(
        Long id,
        String name,
        BigDecimal price,
        Integer vintage,
        String country,
        String region,
        Set<String> grapes,
        String imageUrl
) {
}