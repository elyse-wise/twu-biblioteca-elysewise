package com.twu.biblioteca;

import java.text.ParseException;

/**
 * Created by Elyse on 17/02/2016.
 */
public class CheckoutBookMenuOperation extends MenuOperation {
    public CheckoutBookMenuOperation(String trigger, String description) {
        super(trigger, description);
    }

    @Override
    void execute(Library library, Console console) {
        console.printCheckoutBooksHeader();
        console.printBookList(library.availableBooks());
        console.promptUserForBookSelection();
        Integer selection = getAsInteger(console.getUserCommand());
        if (selection != null && library.bookIsAvailable(selection)) {
            library.checkOutBook(selection);
            console.thankUserForCheckingOut();
        } else {
            console.warnInvalidBookSelection();
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
