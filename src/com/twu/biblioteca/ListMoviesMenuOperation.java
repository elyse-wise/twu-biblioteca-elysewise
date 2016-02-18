package com.twu.biblioteca;

/**
 * Created by Elyse on 17/02/2016.
 */
public class ListMoviesMenuOperation extends MenuOperation {

    public ListMoviesMenuOperation(String trigger, String description) {
        super(trigger, description);
    }

    @Override
    void execute(Library library, Console console) {
        console.printMessage("Movies Currently In Library:");
        console.printLibraryItemList(library.availableMovies());
    }
}
