package eu.babych.winelibrary.dto;

public record ResetPasswordRequestDto(String token, String password) {
}
