package eu.babych.winelibrary.exception.authentication;

public class ExpiredJwtTokenException extends RuntimeException {
    public ExpiredJwtTokenException() {
        super("Your session has expired. Please log in again");
    }
}
