package eu.babych.winelibrary.exception.notfound;

public class StrategyNotFoundException extends EntityNotFoundException {
    public StrategyNotFoundException(Object type) {
        super("No strategy found for: " + type);
    }
}
