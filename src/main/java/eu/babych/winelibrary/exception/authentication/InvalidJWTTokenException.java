package eu.babych.winelibrary.exception.authentication;

public class InvalidJWTTokenException extends RuntimeException {
    public InvalidJWTTokenException() {
        super("Invalid authentication token");
    }
}
