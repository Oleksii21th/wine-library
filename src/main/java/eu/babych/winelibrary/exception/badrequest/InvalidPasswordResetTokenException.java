package eu.babych.winelibrary.exception.badrequest;

public class InvalidPasswordResetTokenException extends BadRequestException {
    public InvalidPasswordResetTokenException() {
        super("Invalid password reset token");
    }
}
