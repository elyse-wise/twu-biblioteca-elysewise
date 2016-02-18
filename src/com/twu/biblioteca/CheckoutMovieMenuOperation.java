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
        console.printCheckoutItemsHeader();
        console.printLibraryItemList(library.availableMovies());
        console.promptUserForItemSelection();
        Integer selection = getAsInteger(console.getUserCommand());
        if (selection != null && library.movieIsAvailable(selection)) {
            library.checkOutMovie(selection);
        }
    }

    private Integer getAsInteger(String userCommand) {
        try {
            return Integer.parseInt(userCommand);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
