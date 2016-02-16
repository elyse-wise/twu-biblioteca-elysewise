package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.List;

/**
 * Created by Elyse on 16/02/2016.
 */
public class Console {

    private PrintStream out;

    public Console(PrintStream out) {
        this.out = out;
    }

    public void printWelcome() {
        out.println("Welcome to Biblioteca!");
    }

    public void printBookList(List<Book> books) {
        if (!books.isEmpty()) {
            out.println(books.get(0).getColumnString());
        }
        for (Book b : books) {
            out.println(b.getDetailsString());
        }
    }

    public void printMenuOptions() {
        out.print("l: ");
        out.println("List Books");
    }
}
