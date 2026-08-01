package eu.babych.winelibrary.exception.badrequest;

public class RefreshTokenExpiredException extends BadRequestException {
    public RefreshTokenExpiredException() {
        super("Refresh token expired");
    }
}
