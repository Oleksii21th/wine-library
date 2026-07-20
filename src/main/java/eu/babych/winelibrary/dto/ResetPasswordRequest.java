package eu.babych.winelibrary.dto;

public record ResetPasswordRequest(String token, String password) {
}
