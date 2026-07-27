package eu.babych.winelibrary.controller;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.dto.wine.WineResponseDto;
import eu.babych.winelibrary.dto.wine.WineSearchRequestDto;
import eu.babych.winelibrary.service.WineService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wines")
public class WineController {
    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @PreAuthorize("hasAnyRole('USER','MANAGER')")
    @GetMapping
    public Page<WineResponseDto> findAll(@ModelAttribute WineFilterRequestDto requestDto,
                                         Pageable pageable) {
        return wineService.findAll(requestDto, pageable);
    }

    @PreAuthorize("hasAnyRole('USER','MANAGER')")
    @GetMapping("/search")
    public Page<WineResponseDto> search(@ModelAttribute WineSearchRequestDto searchDto,
                                        Pageable pageable) {
        return wineService.search(searchDto, pageable);
    }
}
