package eu.babych.winelibrary.service;

import eu.babych.winelibrary.dto.favoritewine.FavoriteWineResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface FavoriteWineService {
    void delete(Authentication authentication, Long wineId);

    Page<FavoriteWineResponseDto> findAllByUser(Authentication authentication,
                                                Pageable pageable);

    FavoriteWineResponseDto save(Authentication authentication, Long wineId);

    boolean isFavorite(Authentication authentication, Long wineId);

    long countFavoriteWines(Authentication authentication);

    Page<FavoriteWineResponseDto> findRecentFavoriteWines(Authentication authentication,
                                                          Pageable pageable);
}
