package eu.babych.winelibrary.controller;

import eu.babych.winelibrary.dto.favoritewine.FavoriteWineResponseDto;
import eu.babych.winelibrary.service.FavoriteWineService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Operation(summary = "Get current user's favorite wines")
    @PreAuthorize("hasAnyRole('USER','MANAGER')")
    @GetMapping
    public Page<FavoriteWineResponseDto> findAll(Authentication authentication,
                                                 Pageable pageable) {
        return favoriteWineService.findAllByUser(authentication, pageable);
    }

    @Operation(summary = "Add wine to current user's favorites")
    @PreAuthorize("hasAnyRole('USER','MANAGER')")
    @PostMapping("/{wineId}")
    public FavoriteWineResponseDto save(Authentication authentication,
                                        @PathVariable Long wineId) {
        return favoriteWineService.save(authentication, wineId);
    }

    @Operation(summary = "Remove wine from current user's favorites")
    @PreAuthorize("hasAnyRole('USER','MANAGER')")
    @DeleteMapping("/{wineId}")
    public void delete(Authentication authentication,
                       @PathVariable Long wineId) {
        favoriteWineService.delete(authentication, wineId);
    }

    @Operation(summary = "Check if wine is in current user's favorites")
    @PreAuthorize("hasAnyRole('USER','MANAGER')")
    @GetMapping("/{wineId}")
    public boolean isFavorite(Authentication authentication,
                              @PathVariable Long wineId) {
        return favoriteWineService.isFavorite(authentication, wineId);
    }

    @Operation(summary = "Returns the number of favorite wines for the authenticated user")
    @PreAuthorize("hasAnyRole('USER','MANAGER')")
    @GetMapping("/count")
    public long countFavoriteWines(Authentication authentication) {
        return favoriteWineService.countFavoriteWines(authentication);
    }

    @Operation(summary = "Returns favorite wines added within the last 30 days")
    @PreAuthorize("hasAnyRole('USER','MANAGER')")
    @GetMapping("/recent")
    public Page<FavoriteWineResponseDto> findRecentFavoriteWines(Authentication authentication,
                                                                 Pageable pageable) {
        return favoriteWineService.findRecentFavoriteWines(authentication, pageable);
    }
}

