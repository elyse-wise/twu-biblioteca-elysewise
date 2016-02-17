package com.twu.biblioteca;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elyse on 17/02/2016.
 */
public class Library {
    private List<Book> availableBooks;
    private List<Book> checkedOutBooks;

    public Library(List<Book> availableBooks) {
        this.availableBooks = availableBooks;
        this.checkedOutBooks = new ArrayList<Book>();
    }

    public Boolean bookIsAvailable(int bookIndex) {
        return (bookIndex >= 0 && bookIndex < availableBooks.size());
    }

    public List<Book> availableBooks() {
        return availableBooks;
    }

    public int numberOfBooksInLibrary() {
        return availableBooks.size();
    }

    public void checkOutBook(int bookIndex) {
        if (bookIsAvailable(bookIndex)) {
            availableBooks.remove(bookIndex);
        }
    }

}
