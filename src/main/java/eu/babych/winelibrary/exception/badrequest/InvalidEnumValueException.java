package eu.babych.winelibrary.exception.badrequest;

public class InvalidEnumValueException extends BadRequestException {
    public InvalidEnumValueException(String field, String value) {
        super("Invalid value for " + field + ": " + value);
    }
}
