package com.twu.biblioteca;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Elyse on 16/02/2016.
 */
public class Console {

    private InputStream in;
    private PrintStream out;

    public Console(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public void printWelcome() {
        out.println("Welcome to Biblioteca!");
    }

    public void printBookList(List<Book> books) {
        out.println("Currently Available Books:");

        for (int i = 0; i < books.size(); i++) {
            if (i == 0) {
                printBookColumns(books.get(0));
            }

            printBookDetails(i, books.get(i));
        }
    }

    public void printBookColumns(Book book) {
        out.print(String.format("\t%-4s", ""));
        out.println(book.getColumnString());
    }

    public void printBookDetails(int i, Book book) {
        out.print(String.format("\t%-4s", i + "."));
        out.println(book.getDetailsString());
    }

    public void printMenuOptions(Map<String, String> menuOptions) {
        out.println("Menu Options:");

        for (Map.Entry<String, String> menuOption : menuOptions.entrySet()) {
            out.print("\t " + menuOption.getKey() + " => ");
            out.println(menuOption.getValue());
        }
    }

    public void promptUserForMenuOption() {
        out.print("> Enter Menu Option: ");
    }

    public void promptUserForBookSelection() {
        out.print("> Enter Book Code: ");
    }

    public String getUserCommand() {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String command = null;
        try {
            command = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return command;
    }

    public void warnInvalidMenuOption() {
        out.println("Select a valid option!");
    }

    public void warnInvalidBookSelection() {
        out.println("That book is not available");
    }

    public void thankUserForCheckingOut() {
        out.println("Thank you! Enjoy the book");
    }

    public void printGap() {
        out.println();
    }

    public void thankUserForReturningBook() {
        out.println("Thank you for returning the book");
    }

    public void warnInvalidBookSelectionForReturn() {
        out.println("That is not a valid book to return");
    }
}
