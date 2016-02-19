package com.twu.biblioteca;

/**
 * Created by Elyse on 19/02/2016.
 */
public class ListCheckedOutBooksMenuOperation extends MenuOperation {

    public ListCheckedOutBooksMenuOperation(String trigger, String description) {
        super(trigger, description);
    }

    @Override
    public void execute(Library library, Console console) {
        console.printMessage("Books Currently Checked Out:");
        console.printCheckedOutBooksBorrowerInformation(library.checkedOutBooks);
    }

    @Override
    public Boolean needsLogin() {
        return true;
    }

    @Override
    public Boolean needsAdministratorLogin() {
        return true;
    }
}
