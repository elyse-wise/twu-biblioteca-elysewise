package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elyse on 17/02/2016.
 */
public class Library {
    private List<Book> availableBooks;
    private List<Book> checkedOutBooks;
    private List<Movie> availableMovies;

    public Library() {
        this.availableBooks = new ArrayList<Book>();
        this.checkedOutBooks = new ArrayList<Book>();
        this.availableMovies = new ArrayList<Movie>();
    }

    public void setAvailableBooks(List<Book> availableBooks) {
        this.availableBooks = availableBooks;
    }

    public void setCheckedOutBooks(List<Book> checkedOutBooks) {
        this.checkedOutBooks = checkedOutBooks;
    }

    public Boolean bookIsAvailable(int bookIndex) {
        return (bookIndex >= 0 && bookIndex < availableBooks.size());
    }

    public Boolean bookIsCheckedOut(int bookIndex) {
        return (bookIndex >= 0 && bookIndex < checkedOutBooks.size());
    }

    public List<Book> availableBooks() {
        return availableBooks;
    }

    public List<Book> checkedOutBooks() {
        return checkedOutBooks;
    }

    public List<Movie> availableMovies() {
        return new ArrayList<Movie>();
    }

    public int numberOfBooksInLibrary() {
        return availableBooks.size();
    }

    public int numberOfBooksCheckedOut() {
        return checkedOutBooks.size();
    }

    public void checkOutBook(int bookIndex) {
        if (bookIsAvailable(bookIndex)) {
            Book b = availableBooks.remove(bookIndex);
            checkedOutBooks.add(b);
        }
    }

    public void returnBook(int bookIndex) {
        if (bookIsCheckedOut(bookIndex)) {
            Book b = checkedOutBooks.remove(bookIndex);
            availableBooks.add(b);
        }
    }
}
