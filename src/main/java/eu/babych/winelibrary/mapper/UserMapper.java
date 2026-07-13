package eu.babych.winelibrary.mapper;

import eu.babych.winelibrary.config.MapperConfig;
import eu.babych.winelibrary.dto.UserRegistrationRequestDto;
import eu.babych.winelibrary.dto.UserRegistrationResponseDto;
import eu.babych.winelibrary.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    @Mapping(source = "username", target = "email")
    UserRegistrationResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto userRegistrationRequestDto);
}
