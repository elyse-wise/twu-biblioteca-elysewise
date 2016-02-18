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
        console.printCheckoutItemsHeader();
        console.printLibraryItemList(library.availableBooks());
        console.promptUserForItemSelection();
        Integer selection = getAsInteger(console.getUserCommand());
        if (selection != null && library.bookIsAvailable(selection)) {
            library.checkOutBook(selection);
            console.thankUserForCheckingOutBook();
        } else {
            console.warnInvalidBookSelectionForCheckout();
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
