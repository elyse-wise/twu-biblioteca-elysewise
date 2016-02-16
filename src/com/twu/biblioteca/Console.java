package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.List;

/**
 * Created by Elyse on 16/02/2016.
 */
public class Console {

    public void printWelcome(PrintStream out) {
        out.println("Welcome to Biblioteca!");
    }

    public void printBookList(List<String> books, PrintStream out) {
        for (String book : books) {
            out.println(book);
        }
    }
}
