package eu.babych.winelibrary.exception.authentication;

public class InvalidJwtTokenException extends RuntimeException {
    public InvalidJwtTokenException() {
        super("Invalid authentication token");
    }
}
