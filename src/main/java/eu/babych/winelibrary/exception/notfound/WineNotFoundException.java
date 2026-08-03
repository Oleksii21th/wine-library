package eu.babych.winelibrary.exception.notfound;

public class WineNotFoundException extends EntityNotFoundException {
    public WineNotFoundException(Long id) {
        super("Wine with id: " + id + " not found.");
    }
}
