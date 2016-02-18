package com.twu.biblioteca;

/**
 * Created by Elyse on 17/02/2016.
 */
public class ListBooksMenuOperation extends MenuOperation {

    public ListBooksMenuOperation(String trigger, String description) {
        super(trigger, description);
    }

    @Override
    void execute(Library library, Console console) {
        console.printListItemsHeader();
        console.printLibraryItemList(library.availableBooks());
    }
}
