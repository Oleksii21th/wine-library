package eu.babych.winelibrary.exception.notfound;

public abstract class EntityNotFoundException extends RuntimeException {
    protected EntityNotFoundException(String message) {
        super(message);
    }
}
