package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Elyse on 16/02/2016.
 */
public class BibliotecaApp {

    private Console console;
    private Library library;
    private Map<String, String> menuOptions;


    public BibliotecaApp(Console console, Library library, Map<String, String> menuOptions) {
        this.console = console;
        this.library = library;
        this.menuOptions = menuOptions;
    }

    public void run() {
        console.printWelcome();
        String command = null;

        while (userHasNotQuit(command)) {

            command = getNextUserCommand();

            if (command != null && command.equalsIgnoreCase("L")) {
                console.printListBooksHeader();
                console.printBookList(library.availableBooks());
            } else if (command != null && command.equalsIgnoreCase("C")) {
                console.printCheckoutBooksHeader();
                console.printBookList(library.availableBooks());
                console.promptUserForBookSelection();
                int selection = Integer.parseInt(console.getUserCommand());
                if (library.bookIsAvailable(selection)) {
                    library.checkOutBook(selection);
                    console.thankUserForCheckingOut();
                } else {
                    console.warnInvalidBookSelection();
                }
            } else if (command != null && command.equalsIgnoreCase("R")) {
                console.printReturnBooksHeader();
                console.printBookList(library.checkedOutBooks());
                console.promptUserForBookSelection();
                int selection = Integer.parseInt(console.getUserCommand());
                if (library.bookIsCheckedOut(selection)) {
                    library.returnBook(selection);
                    console.thankUserForReturningBook();
                } else {
                    console.warnInvalidBookSelectionForReturn();
                }
            } else if (command != null && command.equalsIgnoreCase("Q")) {
                break;
            } else {
                console.warnInvalidMenuOption();
            }
        }
    }

    private Boolean userHasNotQuit(String command) {
        Boolean userHasQuit = (command != null && command.equalsIgnoreCase("Q"));
        return !userHasQuit;
    }

    private String getNextUserCommand() {
        console.printGap();
        console.printMenuOptions(menuOptions);
        console.promptUserForMenuOption();
        return console.getUserCommand();
    }
}
