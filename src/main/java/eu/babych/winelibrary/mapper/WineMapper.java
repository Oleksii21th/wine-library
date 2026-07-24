package eu.babych.winelibrary.mapper;

import eu.babych.winelibrary.config.MapperConfig;
import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.dto.wine.WineFilterResponseDto;
import eu.babych.winelibrary.model.wine.Wine;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface WineMapper {
    Wine toModel(WineFilterRequestDto requestDto);

    WineFilterResponseDto toDto(Wine wine);
}
