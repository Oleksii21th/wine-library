package eu.babych.winelibrary.exceptions;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        super("Incorrect login or password");
    }
}
