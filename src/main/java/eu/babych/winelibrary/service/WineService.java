package eu.babych.winelibrary.service;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.dto.wine.WineResponseDto;
import eu.babych.winelibrary.dto.wine.WineSearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WineService {
    WineResponseDto findById(Long id);

    Page<WineResponseDto> findAll(WineFilterRequestDto requestDto,
                                  Pageable pageable);

    Page<WineResponseDto> search(WineSearchRequestDto searchDto,
                                 Pageable pageable);
}
