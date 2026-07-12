package eu.babych.winelibrary.mapper;

import eu.babych.winelibrary.config.MapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
//    @Mapping(source = "username", target = "email")
//    UserLoginResponseDto toDto(User user);
//
//    User toModel(UserLoginRequestDto userLoginRequestDto);
}
