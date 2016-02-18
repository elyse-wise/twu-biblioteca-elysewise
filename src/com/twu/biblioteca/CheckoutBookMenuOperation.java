package com.twu.biblioteca;

/**
 * Created by Elyse on 17/02/2016.
 */
public class CheckoutBookMenuOperation extends MenuOperation {
    public CheckoutBookMenuOperation(String trigger, String description) {
        super(trigger, description);
    }

    @Override
    void execute(Library library, Console console) {
        console.printMessage("Books available for checkout:");
        console.printLibraryItemList(library.availableBooks());
        console.printMessage("> Enter book number: ");
        console.promptUserForItemSelection();
        Integer selection = getAsInteger(console.getUserCommand());
        if (selection != null && library.bookIsAvailable(selection)) {
            library.checkOutBook(selection);
            console.printMessage("Thank you! Enjoy the book");
        } else {
            console.printMessage("That book is not available");
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

