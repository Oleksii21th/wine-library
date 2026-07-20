package eu.babych.winelibrary.exception.badrequest;

public class InvalidTokenException extends BadRequestException {
    public InvalidTokenException() {
        super("Invalid token");
    }
}
