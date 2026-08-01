package eu.babych.winelibrary.security;

import eu.babych.winelibrary.dto.UserLoginRequestDto;
import eu.babych.winelibrary.dto.UserLoginResponseDto;
import eu.babych.winelibrary.exception.LoginFailedException;
import eu.babych.winelibrary.exception.notfoundexception.RefreshTokenNotFoundException;
import eu.babych.winelibrary.model.RefreshToken;
import eu.babych.winelibrary.model.User;
import eu.babych.winelibrary.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Value("${jwt.refresh-expiration}")
    private long jwtRefreshExpirationMs;

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthenticationService(JwtUtil jwtUtil,
                                 AuthenticationManager authenticationManager,
                                 RefreshTokenRepository refreshTokenRepository) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public UserLoginResponseDto authenticateUser(UserLoginRequestDto request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(), request.password()));

            String accessToken = jwtUtil.generateToken(authentication);
            User user = (User) authentication.getPrincipal();

            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setUser(user);
            refreshToken.setExpiresAt(getRefreshTokenExpiration());
            refreshTokenRepository.save(refreshToken);

            return new UserLoginResponseDto(accessToken, refreshToken.getToken());
        } catch (BadCredentialsException ex) {
            throw new LoginFailedException();
        }
    }

    @Transactional
    public void logout(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(RefreshTokenNotFoundException::new);

        token.setRevoked(true);
    }

    public Instant getRefreshTokenExpiration() {
        return Instant.now().plusMillis(jwtRefreshExpirationMs);
    }
}
