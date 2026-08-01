package eu.babych.winelibrary.controller;

import eu.babych.winelibrary.dto.ForgotPasswordRequestDto;
import eu.babych.winelibrary.dto.LogoutRequestDto;
import eu.babych.winelibrary.dto.RefreshTokenRequestDto;
import eu.babych.winelibrary.dto.ResetPasswordRequestDto;
import eu.babych.winelibrary.dto.UserLoginRequestDto;
import eu.babych.winelibrary.dto.UserLoginResponseDto;
import eu.babych.winelibrary.dto.UserRegistrationRequestDto;
import eu.babych.winelibrary.dto.UserRegistrationResponseDto;
import eu.babych.winelibrary.security.AuthenticationService;
import eu.babych.winelibrary.service.PasswordResetTokenService;
import eu.babych.winelibrary.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Authenticate a user and return a JWT token")
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto loginRequest) {
        return authenticationService.authenticateUser(loginRequest);
    }

    @Operation(summary = "Sign out a user and revoke refresh token")
    @PostMapping("/logout")
    public void logout(@RequestBody LogoutRequestDto request) {
        authenticationService.logout(request.refreshToken());
    }

    @Operation(summary = "Refresh access token using refresh token")
    @PostMapping("/refresh")
    public UserLoginResponseDto refresh(@RequestBody RefreshTokenRequestDto request) {
        return authenticationService.refreshToken(request.refreshToken());
    }

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public UserRegistrationResponseDto register(@RequestBody @Valid
                                                UserRegistrationRequestDto registrationRequest) {
        return userService.register(registrationRequest);
    }

    @Operation(summary = "Send a password reset email")
    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody ForgotPasswordRequestDto request) {
        passwordResetTokenService.forgotPassword(request.email());
    }

    @Operation(summary = "Reset a user password")
    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody ResetPasswordRequestDto request) {
        passwordResetTokenService.resetPassword(request);
    }
}
