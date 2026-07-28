package eu.babych.winelibrary.exception;

import eu.babych.winelibrary.exception.badrequest.BadRequestException;

public class UserAlreadyExistsRegistrationException extends BadRequestException {
    public UserAlreadyExistsRegistrationException(String email) {
        super(String.format("User with email %s already exists", email));
    }
}
