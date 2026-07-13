package eu.babych.winelibrary.dto;

public record UserRegistrationResponseDto(
        Long id,
        String email,
        String fullName
) {
}
