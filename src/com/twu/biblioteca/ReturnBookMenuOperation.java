package com.twu.biblioteca;

/**
 * Created by Elyse on 17/02/2016.
 */
public class ReturnBookMenuOperation extends MenuOperation {
    public ReturnBookMenuOperation(String trigger, String description) {
        super(trigger, description);
    }

    @Override
    void execute(Library library, Console console) {
        console.printMessage("Books available for return:");
        console.printLibraryItemList(library.checkedOutBooks());
        console.printMessage("> Enter book number: ");
        Integer selection = getAsInteger(console.getUserCommand());
        if (selection != null && library.bookIsCheckedOut(selection)) {
            library.returnBook(selection);
            console.printMessage("Thank you for returning the book");
        } else {
            console.printMessage("That is not a valid book to return");
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
