package com.twu.biblioteca;

import java.io.*;
import java.util.List;

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

    public void printMovieList(List<Movie> movies) {
        for (int i = 0; i < movies.size(); i++) {
            if (i == 0) {
                printMovieColumns(movies.get(0));
            }
            printMovieDetails(i, movies.get(i));
        }
    }

    public void printMovieColumns(Movie movie) {
        out.print(String.format("\t%-4s", ""));
        out.println(movie.getColumnString());
    }

    public void printMovieDetails(int i, Movie movie) {
        out.print(String.format("\t%-4s", i + "."));
        out.println(movie.getDetailsString());
    }

    public void printMenuOptions(List<MenuOperation> menuOperations) {
        out.println("Menu Options:");

        for (MenuOperation op : menuOperations) {
            out.print("\t " + op.getTrigger() + " => ");
            out.println(op.getDescription());
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

    public void printListBooksHeader() {
        out.println("Books Currently In Library:");
    }

    public void printCheckoutBooksHeader() {
        out.println("Books Available For Checkout:");
    }

    public void printReturnBooksHeader() {
        out.println("Books Available For Return:");
    }

    public void printExitMessage() {
        out.println("Exiting Biblioteca...");
    }
}
