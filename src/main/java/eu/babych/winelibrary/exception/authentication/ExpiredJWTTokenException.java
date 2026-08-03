package eu.babych.winelibrary.exception.authentication;

public class ExpiredJWTTokenException extends RuntimeException {
    public ExpiredJWTTokenException() {
        super("Your session has expired. Please log in again");
    }
}
