package eu.babych.winelibrary.mapper;

import eu.babych.winelibrary.config.MapperConfig;
import eu.babych.winelibrary.dto.favoritewine.FavoriteWineResponseDto;
import eu.babych.winelibrary.model.FavoriteWine;
import eu.babych.winelibrary.model.wine.Grape;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface FavoriteWineMapper {
    @Mapping(target = "wine.country", source = "wine.country.name")
    @Mapping(target = "wine.region", source = "wine.region.name")
    @Mapping(target = "wine.producer", source = "wine.producer.name")
    FavoriteWineResponseDto toDto(FavoriteWine favoriteWine);

    default Set<String> mapGrapes(Set<Grape> grapes) {
        if (grapes == null) {
            return Set.of();
        }

        return grapes.stream()
                .map(Grape::getName)
                .collect(Collectors.toSet());
    }
}
