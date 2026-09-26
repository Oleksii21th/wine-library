package eu.babych.winelibrary.exception.badrequest;

public class InvalidDurationException extends RuntimeException {
    public InvalidDurationException() {
        super("Duration must be between 1 and 12 hours.");
    }
}
