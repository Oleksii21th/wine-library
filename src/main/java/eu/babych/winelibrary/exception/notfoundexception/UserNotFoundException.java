package eu.babych.winelibrary.exception.notfoundexception;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException() {
        super("User not found");
    }
}
