package com.twu.biblioteca;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Console console = new Console(System.in, System.out);
        Library library = new Library(buildBooks());
        BibliotecaApp application = new BibliotecaApp(console, library, buildMenuOptions());
        application.run();
    }

    private static Map<String, String> buildMenuOptions() {
        Map<String, String> menuOptions = new LinkedHashMap<String, String>();
        menuOptions.put("L", "List Books");
        menuOptions.put("C", "Checkout Book");
        menuOptions.put("R", "Return Book");
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
