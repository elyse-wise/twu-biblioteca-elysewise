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

        while (true) {
            console.printGap();
            console.printMenuOptions(menuOptions);
            console.promptUserForMenuOption();
            String command = console.getUserCommand();

            if (command != null && command.equalsIgnoreCase("L")) {
                console.printBookList(library.availableBooks());
            } else if (command != null && command.equalsIgnoreCase("C")) {
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

}
