package eu.babych.winelibrary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record UserRegistrationRequestDto(
        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Email must be a valid email address")
        String email,

        @NotBlank(message = "Password cannot be empty")
        @Size(min = 4, message = "Password must be at least 4 characters long")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password,

        @NotBlank(message = "Full name cannot be empty")
        String fullName,

        @NotNull(message = "Birth date cannot be null")
        @Past(message = "Birth date must be in the past")
        LocalDate birthDate
){
}
