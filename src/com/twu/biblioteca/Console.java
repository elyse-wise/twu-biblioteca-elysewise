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

    public void printBookList(List<String> books) {
        for (String book : books) {
            out.println(book);
        }
    }
}
