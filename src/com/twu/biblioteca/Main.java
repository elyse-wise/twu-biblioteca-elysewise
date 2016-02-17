package com.twu.biblioteca;

import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Console console = new Console(System.in, System.out);
        Library library = new Library(buildBooks());
        BibliotecaApp application = new BibliotecaApp(console, library, buildMenuOperations());
        application.run();
    }

    private static List<MenuOperation> buildMenuOperations() {
        List<MenuOperation> menuOperations = new ArrayList<MenuOperation>();
        menuOperations.add(new ListBooksMenuOperation("L", "List Books"));
        menuOperations.add(new CheckoutBookMenuOperation("C", "Checkout Book"));
        menuOperations.add(new ReturnBookMenuOperation("R", "Return Book"));
        menuOperations.add(new QuitMenuOperation("Q", "Quit"));
        return menuOperations;
    }

    private static List<Book> buildBooks() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Book A", "Author A", "Year A"));
        books.add(new Book("Book B", "Author B", "Year B"));
        books.add(new Book("Book C", "Author C", "Year C"));
        return books;
    }
}
