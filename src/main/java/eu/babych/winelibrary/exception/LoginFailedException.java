package eu.babych.winelibrary.exception;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        super("Incorrect login or password");
    }
}
