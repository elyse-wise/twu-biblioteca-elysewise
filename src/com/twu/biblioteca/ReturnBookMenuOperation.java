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
        console.printReturnBooksHeader();
        console.printLibraryItemList(library.checkedOutBooks());
        console.promptUserForItemSelection();
        Integer selection = getAsInteger(console.getUserCommand());
        if (selection != null && library.bookIsCheckedOut(selection)) {
            library.returnBook(selection);
            console.thankUserForReturningBook();
        } else {
            console.warnInvalidBookSelectionForReturn();
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
