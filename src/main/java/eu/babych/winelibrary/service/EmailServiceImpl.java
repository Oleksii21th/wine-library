package eu.babych.winelibrary.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${forgot-password.token.expiration}")
    private Integer forgotPasswordTokenExpiration;

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendResetPasswordEmail(String to, String token) {
        String link = "http://localhost:3000/reset-password?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("redbul123450@wp.pl");
        message.setTo(to);
        message.setSubject("Reset Your Wine Library Password");
        message.setText("""
                Hello,
                
                We received a request to reset the password for your Wine Library account.
                
                To create a new password, click the link below:
                
                %s
                
                This link will expire in %d minutes.
                
                If you didn't request a password reset, you can safely ignore this email.
                """.formatted(link, forgotPasswordTokenExpiration));

        mailSender.send(message);
    }
}
