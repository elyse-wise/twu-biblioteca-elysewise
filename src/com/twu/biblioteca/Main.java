package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Console console = new Console(System.in, System.out);
        Library library = new Library(buildBooks());
        BibliotecaApp application = new BibliotecaApp(console, library, buildMenuOptions());
        application.run();
    }

    private static Map<String, String> buildMenuOptions() {
        Map<String, String> menuOptions = new HashMap<String, String>();
        menuOptions.put("L", "List Books");
        menuOptions.put("C", "Checkout Book");
        menuOptions.put("Q", "Quit");
        return menuOptions;
    }

    private static List<Book> buildBooks() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Book A", "Author A", "Year A"));
        books.add(new Book("Book B", "Author B", "Year B"));
        books.add(new Book("Book C", "Author C", "Year C"));
        return books;
    }
}
