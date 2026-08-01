package eu.babych.winelibrary.exception.notfoundexception;

public class RefreshTokenNotFoundException extends EntityNotFoundException {
    public RefreshTokenNotFoundException() {
        super("Refresh token not found");
    }
}
