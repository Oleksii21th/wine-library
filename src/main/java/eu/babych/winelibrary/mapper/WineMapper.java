package eu.babych.winelibrary.mapper;

import eu.babych.winelibrary.config.MapperConfig;
import eu.babych.winelibrary.dto.wine.WineResponseDto;
import eu.babych.winelibrary.model.wine.Food;
import eu.babych.winelibrary.model.wine.Grape;
import eu.babych.winelibrary.model.wine.Wine;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface WineMapper {
    @Mapping(target = "country", source = "wine.country.name")
    @Mapping(target = "region", source = "wine.region.name")
    @Mapping(target = "producer", source = "wine.producer.name")
    WineResponseDto toDto(Wine wine, boolean favorite);

    @Named("favoriteWine")
    default WineResponseDto toFavoriteDto(Wine wine) {
        return toDto(wine, true);
    }

    default Set<String> mapGrapes(Set<Grape> grapes) {
        if (grapes == null) {
            return Set.of();
        }

        return grapes.stream()
                .map(Grape::getName)
                .collect(Collectors.toSet());
    }

    default Set<String> mapFoods(Set<Food> foods) {
        if (foods == null) {
            return Set.of();
        }

        return foods.stream()
                .map(Food::getName)
                .collect(Collectors.toSet());
    }
}
