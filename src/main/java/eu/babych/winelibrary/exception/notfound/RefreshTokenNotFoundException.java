package eu.babych.winelibrary.exception.notfound;

public class RefreshTokenNotFoundException extends EntityNotFoundException {
    public RefreshTokenNotFoundException() {
        super("Refresh token not found");
    }
}
