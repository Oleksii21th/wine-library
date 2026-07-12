package eu.babych.winelibrary.security;

import eu.babych.winelibrary.dto.UserLoginRequestDto;
import eu.babych.winelibrary.dto.UserLoginResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public UserLoginResponseDto authenticateUser(UserLoginRequestDto request) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            request.email(), request.password()));

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
            System.out.println("Login successful");
            return new UserLoginResponseDto("Login successful");
        } catch (BadCredentialsException ex) {
            throw new RuntimeException("failed");
        }
    }
}
