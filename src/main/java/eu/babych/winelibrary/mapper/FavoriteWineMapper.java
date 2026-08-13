package eu.babych.winelibrary.mapper;

import eu.babych.winelibrary.config.MapperConfig;
import eu.babych.winelibrary.dto.favoritewine.FavoriteWineResponseDto;
import eu.babych.winelibrary.model.FavoriteWine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = WineMapper.class)
public interface FavoriteWineMapper {
    @Mapping(target = "wine", source = "wine", qualifiedByName = "favoriteWine")
    FavoriteWineResponseDto toDto(FavoriteWine favoriteWine);
}
