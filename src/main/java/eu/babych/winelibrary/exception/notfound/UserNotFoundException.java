package eu.babych.winelibrary.exception.notfound;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException() {
        super("User not found");
    }
}
