package eu.babych.winelibrary.controller;

import eu.babych.winelibrary.dto.UserLoginRequestDto;
import eu.babych.winelibrary.dto.UserLoginResponseDto;
import eu.babych.winelibrary.dto.UserRegistrationRequestDto;
import eu.babych.winelibrary.dto.UserRegistrationResponseDto;
import eu.babych.winelibrary.security.AuthenticationService;
import eu.babych.winelibrary.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService,
                                    UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto loginRequest) {
        return authenticationService.authenticateUser(loginRequest);
    }

    @PostMapping("/register")
    public UserRegistrationResponseDto register(@RequestBody @Valid
                                                UserRegistrationRequestDto registrationRequest) {
        return userService.register(registrationRequest);
    }
}
