package eu.babych.winelibrary.service;

import eu.babych.winelibrary.dto.ResetPasswordRequestDto;

public interface PasswordResetTokenService {
    void forgotPassword(String email);

    void resetPassword(ResetPasswordRequestDto request);
}
