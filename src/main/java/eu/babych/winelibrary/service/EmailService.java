package eu.babych.winelibrary.service;

public interface EmailService {
    void sendResetPasswordEmail(String to, String token);
}
