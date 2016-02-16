package com.twu.biblioteca;

import java.io.InputStream;
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

    public void run() {
        console.printWelcome();
        console.printMenuOptions(menuOptions);
        console.promptUser();
        String command = console.getUserCommand();

        if (command != null && command.equals("l")) {
            console.printBookList(books);
        }
    }

}
