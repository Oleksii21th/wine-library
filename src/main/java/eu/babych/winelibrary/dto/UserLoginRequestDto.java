package eu.babych.winelibrary.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDto(
        @NotBlank(message = "email cannot be empty")
        @Email(message = "Email must be valid")
        String email,
        @NotBlank(message = "Password cannot be empty")
        String password) {
}
