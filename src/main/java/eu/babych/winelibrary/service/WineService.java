package eu.babych.winelibrary.service;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.dto.wine.WineFilterResponseDto;
import java.util.List;

public interface WineService {
    List<WineFilterResponseDto> search(WineFilterRequestDto requestDto);
}
