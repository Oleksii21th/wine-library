package eu.babych.winelibrary.service;

import eu.babych.winelibrary.dto.UserLoginRequestDto;
import eu.babych.winelibrary.dto.UserLoginResponseDto;

public interface UserService {
    UserLoginResponseDto register(UserLoginRequestDto user);
}
