package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Console console = new Console();
        console.printWelcome(System.out);

        List<String> books = new ArrayList<String>();
        books.add("Book A");
        books.add("Book B");
        books.add("Book C");

        console.printBookList(books, System.out);
    }
}
