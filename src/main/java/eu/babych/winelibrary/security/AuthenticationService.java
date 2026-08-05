package eu.babych.winelibrary.security;

import eu.babych.winelibrary.dto.UserLoginRequestDto;
import eu.babych.winelibrary.dto.UserLoginResponseDto;
import eu.babych.winelibrary.exception.authentication.LoginFailedException;
import eu.babych.winelibrary.exception.badrequest.RefreshTokenExpiredException;
import eu.babych.winelibrary.exception.badrequest.RefreshTokenRevokedException;
import eu.babych.winelibrary.exception.notfound.RefreshTokenNotFoundException;
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

            User user = (User) authentication.getPrincipal();

            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setUser(user);
            refreshToken.setExpiresAt(getRefreshTokenExpiration());
            refreshTokenRepository.save(refreshToken);

            String accessToken = jwtUtil.generateToken(authentication);

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

    @Transactional
    public UserLoginResponseDto refreshToken(String refreshTokenValue) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenValue)
                .orElseThrow(RefreshTokenNotFoundException::new);

        if (refreshToken.isRevoked()) {
            throw new RefreshTokenRevokedException();
        }

        if (refreshToken.getExpiresAt().isBefore(Instant.now())) {
            throw new RefreshTokenExpiredException();
        }

        User user = refreshToken.getUser();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        String newAccessToken = jwtUtil.generateToken(authentication);

        return new UserLoginResponseDto(newAccessToken, refreshToken.getToken());
    }

    public Instant getRefreshTokenExpiration() {
        return Instant.now().plusMillis(jwtRefreshExpirationMs);
    }
}
