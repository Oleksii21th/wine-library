package eu.babych.winelibrary.service;

import eu.babych.winelibrary.dto.ResetPasswordRequest;

public interface PasswordResetTokenService {
    void forgotPassword(String email);

    void resetPassword(ResetPasswordRequest request);
}
