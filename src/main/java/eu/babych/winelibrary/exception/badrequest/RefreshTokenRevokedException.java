package eu.babych.winelibrary.exception.badrequest;

public class RefreshTokenRevokedException extends BadRequestException {
    public RefreshTokenRevokedException() {
        super("Refresh token revoked");
    }
}
