package com.twu.biblioteca;

/**
 * Created by Elyse on 18/02/2016.
 */
public class CheckoutMovieMenuOperation extends MenuOperation {
    public CheckoutMovieMenuOperation(String trigger, String description) {
        super(trigger, description);
    }

    @Override
    void execute(Library library, Console console) {
        if (library.numberOfMoviesInLibrary() == 0) {
            console.printMessage("There are no movies available for checkout");
            return;
        }
        console.printMessage("Movies available for checkout:");
        console.printLibraryItemList(library.availableMovies());
        console.printMessage("> Enter movie number: ");
        Integer selection = getAsInteger(console.getUserCommand());
        if (selection != null && library.movieIsAvailable(selection)) {
            library.checkOutMovie(selection);
            console.printMessage("Thank you! Enjoy the movie");
        } else {
            console.printMessage("That movie is not available");
        }
    }

    private Integer getAsInteger(String userCommand) {
        try {
            return Integer.parseInt(userCommand);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Boolean needsLogin() {
        return true;
    }
}
