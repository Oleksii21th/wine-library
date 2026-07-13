package eu.babych.winelibrary.service;

import eu.babych.winelibrary.dto.UserRegistrationRequestDto;
import eu.babych.winelibrary.dto.UserRegistrationResponseDto;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto registrationRequestDto);
}
