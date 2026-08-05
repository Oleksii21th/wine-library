package eu.babych.winelibrary.exception.badrequest;

public class FavoriteWineAlreadyExistsException extends BadRequestException {
    public FavoriteWineAlreadyExistsException(Long wineId) {
        super(String.format("Favorite wine with ID '%s' already exists", wineId));
    }
}
