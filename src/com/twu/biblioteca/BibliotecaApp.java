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
    private List<MenuOperation> menuOperations;


    public BibliotecaApp(Console console, Library library, List<MenuOperation> menuOperations) {
        this.console = console;
        this.library = library;
        this.menuOperations = menuOperations;
    }

    public void run() {
        console.printWelcome();
        MenuOperation op = null;

        while (!userHasQuit(op)) {

            op = findMatchingMenuOperation(getNextUserCommand());
            if (op != null) {
                op.execute(library, console);
            } else {
                console.warnInvalidMenuOption();
            }
        }
    }

    private MenuOperation findMatchingMenuOperation(String command) {
        for (MenuOperation op : menuOperations) {
            if (op.isTriggeredBy(command)) {
                return op;
            }
        }
        return null;
    }

    private Boolean userHasQuit(MenuOperation op) {
        return (op instanceof QuitMenuOperation);
    }

    private String getNextUserCommand() {
        console.printGap();
        console.printMenuOptions(menuOperations);
        console.promptUserForMenuOption();
        return console.getUserCommand();
    }
}
