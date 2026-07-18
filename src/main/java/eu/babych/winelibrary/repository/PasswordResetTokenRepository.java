package eu.babych.winelibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends
        JpaRepository<PasswordResetTokenRepository, Long> {
}
