package com.twu.biblioteca;

import java.util.List;
import java.util.Map;

/**
 * Created by Elyse on 16/02/2016.
 */
public class BibliotecaApp {

    private Console console;
    private List<Book> books;
    private Map<String, String> menuOptions;


    public BibliotecaApp(Console console, List<Book> books, Map<String, String> menuOptions) {
        this.console = console;
        this.books = books;
        this.menuOptions = menuOptions;
    }

    public int numberOfBooksInLibrary() {
        return books.size();
    }

    public void run() {
        console.printWelcome();

        while (true) {
            console.printGap();
            console.printMenuOptions(menuOptions);
            console.promptUserForMenuOption();
            String command = console.getUserCommand();

            if (command != null && command.equalsIgnoreCase("L")) {
                console.printBookList(books);
            } else if (command != null && command.equalsIgnoreCase("C")) {
                console.printBookList(books);
                console.promptUserForBookSelection();
                int selection = Integer.parseInt(console.getUserCommand());
                if (selection > 0 && selection < books.size()) {
                    books.remove(selection);
                    console.thankUserForCheckingOut();
                }
            } else if (command != null && command.equalsIgnoreCase("Q")) {
                break;
            } else {
                console.warnInvalidMenuOption();
            }
        }
    }

}
