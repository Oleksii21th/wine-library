package eu.babych.winelibrary.controller;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.dto.wine.WineFilterResponseDto;
import eu.babych.winelibrary.service.WineService;
import java.util.List;
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
    @GetMapping("/search")
    public List<WineFilterResponseDto> search(@ModelAttribute WineFilterRequestDto requestDto) {
        return wineService.search(requestDto);
    }
}
