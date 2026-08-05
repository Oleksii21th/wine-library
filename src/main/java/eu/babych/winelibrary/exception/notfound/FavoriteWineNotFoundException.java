package eu.babych.winelibrary.exception.notfound;

public class FavoriteWineNotFoundException extends EntityNotFoundException {
    public FavoriteWineNotFoundException() {
        super("Favorite wine not found");
    }
}
