package eu.babych.winelibrary.controller;

import eu.babych.winelibrary.dto.UserLoginRequestDto;
import eu.babych.winelibrary.dto.UserLoginResponseDto;
import eu.babych.winelibrary.security.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto loginRequest) {
        return authenticationService.authenticateUser(loginRequest);
    }
}
