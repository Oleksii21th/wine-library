package eu.babych.winelibrary.service;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.dto.wine.WineResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface WineService {
    List<WineResponseDto> search(WineFilterRequestDto requestDto);

    List<WineResponseDto> findAll(Pageable pageable);
}
