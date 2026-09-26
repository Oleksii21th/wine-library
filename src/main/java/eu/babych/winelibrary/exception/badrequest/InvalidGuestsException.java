package eu.babych.winelibrary.exception.badrequest;

public class InvalidGuestsException extends BadRequestException {
    public InvalidGuestsException() {
        super("Guests must be between 1 and 100.");
    }
}
