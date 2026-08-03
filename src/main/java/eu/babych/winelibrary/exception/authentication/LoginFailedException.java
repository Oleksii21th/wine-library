package eu.babych.winelibrary.exception.authentication;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        super("Incorrect login or password");
    }
}
