package eu.babych.winelibrary.mapper;

import eu.babych.winelibrary.config.MapperConfig;
import eu.babych.winelibrary.dto.wine.WineResponseDto;
import eu.babych.winelibrary.model.wine.Grape;
import eu.babych.winelibrary.model.wine.Wine;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface WineMapper {
    @Mapping(target = "country", source = "country.name")
    @Mapping(target = "region", source = "region.name")
    @Mapping(target = "producer", source = "producer.name")
    WineResponseDto toDto(Wine wine);

    default Set<String> mapGrapes(Set<Grape> grapes) {
        if (grapes == null) {
            return Set.of();
        }

        return grapes.stream()
                .map(Grape::getName)
                .collect(Collectors.toSet());
    }
}
