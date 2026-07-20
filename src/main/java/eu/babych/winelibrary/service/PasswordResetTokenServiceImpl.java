package eu.babych.winelibrary.service;

import eu.babych.winelibrary.dto.ResetPasswordRequest;
import eu.babych.winelibrary.model.PasswordResetToken;
import eu.babych.winelibrary.model.User;
import eu.babych.winelibrary.repository.PasswordResetTokenRepository;
import eu.babych.winelibrary.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    @Value("${forgot-password.token.expiration}")
    private Integer forgotPasswordTokenExpiration;

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository passwordResetRepository;
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordResetTokenServiceImpl(UserRepository userRepository,
                                         PasswordResetTokenRepository passwordResetRepository,
                                         EmailService emailService,
                                         BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordResetRepository = passwordResetRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void forgotPassword(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        passwordResetRepository.deleteByUser(user);

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiresAt(LocalDateTime.now().plusMinutes(forgotPasswordTokenExpiration));
        passwordResetRepository.save(resetToken);

        emailService.sendResetPasswordEmail(user.getEmail(), token);
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {

        PasswordResetToken token = passwordResetRepository.findByToken(request.token())
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        User user = token.getUser();
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
        passwordResetRepository.delete(token);
    }
}
