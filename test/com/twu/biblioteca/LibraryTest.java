package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

/**
 * Created by Elyse on 17/02/2016.
 */
public class LibraryTest {

    private Library library;


    @Before
    public void setup() {
        //start with library of 2 available books, 3 checked out books
        library = new Library(buildListOfMockBooksWithSetSize(2), buildListOfMockBooksWithSetSize(3));
    }

    private List<Book> buildListOfMockBooksWithSetSize(int amountOfBooks) {
        List<Book> bookList = new ArrayList<Book>();
        for (int i = 0; i < amountOfBooks; i++) {
            bookList.add(mock(Book.class));
        }
        return bookList;
    }

    @Test
    public void testBookZeroIsAvailableWhenBooksAreInLibrary() {
        assertTrue(library.bookIsAvailable(0));
    }

    @Test
    public void testBookZeroIsCheckedOutWhenBooksAreCheckedOutOfLibrary() {
        assertTrue(library.bookIsAvailable(0));
    }

    @Test
    public void testBookZeroIsNotAvailableWhenNoBooksAreInLibrary() {
        library = new Library(buildListOfMockBooksWithSetSize(0));
        assertFalse(library.bookIsAvailable(0));
    }

    @Test
    public void testBookZeroIsNotCheckedOutWhenNoBooksAreCheckedOutOfLibrary() {
        library = new Library(buildListOfMockBooksWithSetSize(0), buildListOfMockBooksWithSetSize(0));
        assertFalse(library.bookIsCheckedOut(0));
    }

    @Test
    public void numberOfBooksInLibraryReturnsZeroForEmptyBookList() {
        library = new Library(new ArrayList<Book>());
        assertEquals(0, library.numberOfBooksInLibrary());
    }

    @Test
    public void numberOfBooksCheckedOutReturnsZeroForEmptyBookList() {
        library = new Library(new ArrayList<Book>(), new ArrayList<Book>());
        assertEquals(0, library.numberOfBooksCheckedOut());
    }

    @Test
    public void numberOfBooksInLibraryReturnsTwoForBookListOfTwo() {
        assertEquals(2, library.numberOfBooksInLibrary());
    }

    @Test
    public void numberOfBooksCheckedOutReturnsThreeForBookListOfThreee() {
        assertEquals(3, library.numberOfBooksCheckedOut());
    }

    @Test
    public void testValidCheckoutBookOptionRemovesBookFromList() {
        library.checkOutBook(0);
        assertEquals(1, library.numberOfBooksInLibrary());
    }

    @Test
    public void testValidCheckoutBookOptionAddsBookToCheckedOutList() {
        library.checkOutBook(0);
        assertEquals(4, library.numberOfBooksCheckedOut());
    }

    @Test
    public void testInvalidCheckoutBookOptionDoesNotRemoveBookFromList() {
        library.checkOutBook(54398);
        assertEquals(2, library.numberOfBooksInLibrary());
    }

    @Test
    public void testInvalidCheckoutBookOptionDoesNotAddBookToCheckedOutList() {
        library.checkOutBook(54398);
        assertEquals(3, library.numberOfBooksCheckedOut());
    }

    @Test
    public void testValidReturnBookOptionAddsBookToAvailableList() {
        library.returnBook(0);
        assertEquals(3, library.numberOfBooksInLibrary());
    }

    @Test
    public void testValidReturnBookOptionRemovesBookFromCheckedOutList() {
        library.returnBook(0);
        assertEquals(2, library.numberOfBooksCheckedOut());
    }

    @Test
    public void testInvalidReturnBookOptionDoesNotAddBookToAvailableList() {
        library.returnBook(54398);
        assertEquals(2, library.numberOfBooksInLibrary());
    }

    @Test
    public void testInvalidReturnBookOptionDoesNotRemoveBookFromCheckedOutList() {
        library.returnBook(54398);
        assertEquals(3, library.numberOfBooksCheckedOut());
    }
}