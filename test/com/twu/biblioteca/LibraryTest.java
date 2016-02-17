package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by Elyse on 17/02/2016.
 */
public class LibraryTest {

    private Library library;


    @Before
    public void setup() {
        List<Book> books = new ArrayList<Book>();
        books.add(mock(Book.class));
        books.add(mock(Book.class));

        library = new Library(books);
    }

    @Test
    public void testBookZeroIsAvailableWhenBooksAreInLibrary() {
        assertTrue(library.bookIsAvailable(0));
    }

    @Test
    public void testBookZeroIsNotAvailableWhenNoBooksAreInLibrary() {
        library = new Library(new ArrayList<Book>());
        assertFalse(library.bookIsAvailable(0));
    }

    @Test
    public void numberOfBooksInLibraryReturnsZeroForEmptyBookList() {
        library = new Library(new ArrayList<Book>());
        assertEquals(0, library.numberOfBooksInLibrary());
    }

    @Test
    public void numberOfBooksInLibraryReturnsTwoForBookListOfTwo() {
        assertEquals(2, library.numberOfBooksInLibrary());
    }

    @Test
    public void testValidCheckoutBookOptionRemovesBookFromList() {
        library.checkOutBook(0);
        assertEquals(1, library.numberOfBooksInLibrary());
    }

    @Test
    public void testInvalidCheckoutBookOptionDoesNotRemoveBookFromList() {
        library.checkOutBook(54398);
        assertEquals(2, library.numberOfBooksInLibrary());
    }
}