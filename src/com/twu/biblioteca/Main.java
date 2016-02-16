package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Console console = new Console(System.out);
        console.printWelcome();

        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Book A", "Author A", "Year A"));
        books.add(new Book("Book B", "Author B", "Year B"));
        books.add(new Book("Book C", "Author C", "Year C"));

        console.printMenuOptions();
        String command = console.getUserCommand(System.in);

        if (command.equals("l")) {
            console.printBookList(books);
        }
    }
}
