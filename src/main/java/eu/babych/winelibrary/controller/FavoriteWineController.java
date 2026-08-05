package eu.babych.winelibrary.controller;

import eu.babych.winelibrary.dto.favoritewine.FavoriteWineResponseDto;
import eu.babych.winelibrary.service.FavoriteWineService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteWineController {
    private final FavoriteWineService favoriteWineService;

    public FavoriteWineController(FavoriteWineService favoriteWineService) {
        this.favoriteWineService = favoriteWineService;
    }

    @GetMapping
    public Page<FavoriteWineResponseDto> findAll(Authentication authentication,
                                                 Pageable pageable) {
        return favoriteWineService.findAllByUser(authentication, pageable);
    }

    @PostMapping("/{wineId}")
    public FavoriteWineResponseDto save(Authentication authentication,
                                        @PathVariable Long wineId) {
        return favoriteWineService.save(authentication, wineId);
    }

    @DeleteMapping("/{wineId}")
    public void delete(Authentication authentication, @PathVariable Long wineId) {
        favoriteWineService.delete(authentication, wineId);
    }

    @GetMapping("/{wineId}")
    public boolean isFavorite(Authentication authentication, @PathVariable Long wineId) {
        return favoriteWineService.isFavorite(authentication, wineId);
    }
}

