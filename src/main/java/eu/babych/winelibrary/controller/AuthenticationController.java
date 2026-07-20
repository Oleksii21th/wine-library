package eu.babych.winelibrary.controller;

import eu.babych.winelibrary.dto.ForgotPasswordRequest;
import eu.babych.winelibrary.dto.ResetPasswordRequest;
import eu.babych.winelibrary.dto.UserLoginRequestDto;
import eu.babych.winelibrary.dto.UserLoginResponseDto;
import eu.babych.winelibrary.dto.UserRegistrationRequestDto;
import eu.babych.winelibrary.dto.UserRegistrationResponseDto;
import eu.babych.winelibrary.security.AuthenticationService;
import eu.babych.winelibrary.service.PasswordResetTokenService;
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
    private final PasswordResetTokenService passwordResetTokenService;

    public AuthenticationController(AuthenticationService authenticationService,
                                    UserService userService,
                                    PasswordResetTokenService passwordResetTokenService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.passwordResetTokenService = passwordResetTokenService;
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

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody ForgotPasswordRequest request) {
        passwordResetTokenService.forgotPassword(request.email());
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody ResetPasswordRequest request) {
        passwordResetTokenService.resetPassword(request);
    }
}
