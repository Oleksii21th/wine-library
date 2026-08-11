package eu.babych.winelibrary.controller;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.dto.wine.WineResponseDto;
import eu.babych.winelibrary.dto.wine.WineSearchRequestDto;
import eu.babych.winelibrary.service.WineService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wines")
public class WineController {
    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @Operation(summary = "Get wine by ID")
    @PreAuthorize("hasAnyRole('USER','MANAGER')")
    @GetMapping("/{id}")
    public WineResponseDto findById(@PathVariable Long id, Authentication authentication) {
        return wineService.findById(id, authentication);
    }

    @Operation(summary = "Get all wines with filtering and pagination")
    @PreAuthorize("hasAnyRole('USER','MANAGER')")
    @GetMapping
    public Page<WineResponseDto> findAll(@ModelAttribute WineFilterRequestDto requestDto,
                                         Pageable pageable,
                                         Authentication authentication) {
        return wineService.findAll(requestDto, pageable, authentication);
    }

    @Operation(summary = "Search wines by name field with pagination")
    @PreAuthorize("hasAnyRole('USER','MANAGER')")
    @GetMapping("/search")
    public Page<WineResponseDto> search(@ModelAttribute WineSearchRequestDto searchDto,
                                        Pageable pageable,
                                        Authentication authentication) {
        return wineService.search(searchDto, pageable, authentication);
    }
}
