package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Elyse on 17/02/2016.
 */
public class LibraryTest {

    private Library library;


    @Before
    public void setup() {
        //start with library of 2 available books, 3 checked out books
        library = new Library();
        library.setAvailableBooks(buildListOfMockBooksWithSetSize(2));
        library.setCheckedOutBooks(buildListOfMockBooksWithSetSize(3));
    }

    private List<Book> buildListOfMockBooksWithSetSize(int amountOfBooks) {
        List<Book> bookList = new ArrayList<Book>();
        for (int i = 0; i < amountOfBooks; i++) {
            bookList.add(mock(Book.class));
        }
        return bookList;
    }

    private List<Movie> buildListOfMockMoviesWithSetSize(int amountOfMovies) {
        List<Movie> movieList = new ArrayList<Movie>();
        for (int i = 0; i < amountOfMovies; i++) {
            movieList.add(mock(Movie.class));
        }
        return movieList;
    }

    @Test
    public void testSetAvailableBooksUpdatesNumberOfBooksInLibrary() {
        List<Book> mockBookList = buildListOfMockBooksWithSetSize(76);
        library.setAvailableBooks(mockBookList);
        assertEquals(76, library.numberOfBooksInLibrary());
    }

    @Test
    public void testSetAvailableBooksUpdatesAvailableBooksInLibrary() {
        List<Book> mockBookList = buildListOfMockBooksWithSetSize(76);
        library.setAvailableBooks(mockBookList);
        assertEquals(mockBookList, library.availableBooks());
    }

    @Test
    public void testSetCheckedOutBooksUpdatesNumberOfBooksCheckedOut() {
        List<Book> mockBookList = buildListOfMockBooksWithSetSize(76);
        library.setCheckedOutBooks(mockBookList);
        assertEquals(76, library.numberOfBooksCheckedOut());
    }

    @Test
    public void testSetCheckedOutBooksUpdatesCheckedOutBooksInLibrary() {
        List<Book> mockBookList = buildListOfMockBooksWithSetSize(76);
        library.setCheckedOutBooks(mockBookList);
        assertEquals(mockBookList, library.checkedOutBooks());
    }

    @Test
    public void testSetAvailableMoviesUpdatesNumberOfMoviesInLibrary() {
        List<Movie> mockMovieList = buildListOfMockMoviesWithSetSize(76);
        library.setAvailableMovies(mockMovieList);
        assertEquals(76, library.numberOfMoviesInLibrary());
    }

    @Test
    public void testSetAvailableMoviesUpdatesAvailableMoviesInLibrary() {
        List<Movie> mockMovieList = buildListOfMockMoviesWithSetSize(76);
        library.setAvailableMovies(mockMovieList);
        assertEquals(mockMovieList, library.availableMovies());
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
        library.setAvailableBooks(buildListOfMockBooksWithSetSize(0));
        assertFalse(library.bookIsAvailable(0));
    }

    @Test
    public void testBookZeroIsNotCheckedOutWhenNoBooksAreCheckedOutOfLibrary() {
        library.setCheckedOutBooks(buildListOfMockBooksWithSetSize(0));
        assertFalse(library.bookIsCheckedOut(0));
    }

    @Test
    public void numberOfBooksInLibraryReturnsZeroForEmptyBookList() {
        library.setAvailableBooks(buildListOfMockBooksWithSetSize(0));
        assertEquals(0, library.numberOfBooksInLibrary());
    }

    @Test
    public void numberOfBooksCheckedOutReturnsZeroForEmptyBookList() {
        library.setCheckedOutBooks(buildListOfMockBooksWithSetSize(0));
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