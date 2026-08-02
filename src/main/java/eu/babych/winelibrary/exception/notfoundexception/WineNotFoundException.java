package eu.babych.winelibrary.exception.notfoundexception;

public class WineNotFoundException extends EntityNotFoundException {
    public WineNotFoundException(Long id) {
        super("Wine with id: " + id + " not found.");
    }
}
