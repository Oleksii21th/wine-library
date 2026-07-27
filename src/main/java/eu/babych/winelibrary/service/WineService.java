package eu.babych.winelibrary.service;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.dto.wine.WineResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WineService {
    Page<WineResponseDto> findAll(WineFilterRequestDto requestDto,
                                  Pageable pageable);
}
