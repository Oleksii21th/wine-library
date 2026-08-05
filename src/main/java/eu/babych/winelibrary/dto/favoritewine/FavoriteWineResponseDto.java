package eu.babych.winelibrary.dto.favoritewine;

import eu.babych.winelibrary.dto.wine.WineResponseDto;
import java.time.LocalDateTime;

public record FavoriteWineResponseDto(Long id,
        WineResponseDto wine,
        LocalDateTime addedAt) {
}
