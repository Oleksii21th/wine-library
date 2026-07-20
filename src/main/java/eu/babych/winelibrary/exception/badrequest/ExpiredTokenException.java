package eu.babych.winelibrary.exception.badrequest;

public class ExpiredTokenException extends BadRequestException {
    public ExpiredTokenException() {
        super("Token expired");
    }
}
