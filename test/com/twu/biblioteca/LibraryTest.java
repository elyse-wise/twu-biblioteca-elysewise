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
        //start with library of 2 available books, 3 checked out books, 5 available movies
        library = new Library();
        library.setAvailableBooks(buildListOfMockBooksWithSetSize(2));
        library.setCheckedOutBooks(buildListOfMockBooksWithSetSize(3));
        library.setAvailableMovies(buildListOfMockMoviesWithSetSize(5));
        library.setUserAccounts(buildUserAccounts());
    }

    private List<User> buildUserAccounts() {
        List<User> userAccounts = new ArrayList<User>();
        userAccounts.add(new User("123-4567", "myPassword"));
        return userAccounts;
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
    public void getUserLoggedInIsFalseByDefault() {
        assertFalse(library.userLoggedIn());
    }

    @Test
    public void getUserLoggedInAsTrueIfUserIsLoggedIn() {
        library.setUserLoggedIn(true);
        assertTrue(library.userLoggedIn());
    }

    @Test
    public void getUserLoggedInAsTrueIfUserIsNotLoggedIn() {
        library.setUserLoggedIn(false);
        assertFalse(library.userLoggedIn());
    }

    /* Books */

    // Books in Library

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
    public void testBookZeroIsAvailableWhenBooksAreInLibrary() {
        assertTrue(library.bookIsAvailable(0));
    }

    @Test
    public void testBookZeroIsNotAvailableWhenNoBooksAreInLibrary() {
        library.setAvailableBooks(buildListOfMockBooksWithSetSize(0));
        assertFalse(library.bookIsAvailable(0));
    }

    @Test
    public void numberOfBooksInLibraryReturnsZeroForEmptyBookList() {
        library.setAvailableBooks(buildListOfMockBooksWithSetSize(0));
        assertEquals(0, library.numberOfBooksInLibrary());
    }

    @Test
    public void numberOfBooksInLibraryReturnsTwoForBookListOfTwo() {
        assertEquals(2, library.numberOfBooksInLibrary());
    }

    // Checkout Books

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
    public void numberOfBooksCheckedOutReturnsZeroForEmptyBookList() {
        library.setCheckedOutBooks(buildListOfMockBooksWithSetSize(0));
        assertEquals(0, library.numberOfBooksCheckedOut());
    }

    @Test
    public void testBookZeroIsCheckedOutWhenBooksAreCheckedOutOfLibrary() {
        assertTrue(library.bookIsCheckedOut(0));
    }

    @Test
    public void testBookZeroIsNotCheckedOutWhenNoBooksAreCheckedOutOfLibrary() {
        library.setCheckedOutBooks(buildListOfMockBooksWithSetSize(0));
        assertFalse(library.bookIsCheckedOut(0));
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

    // Return Books

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

    /* Movies */

    // Movies in Library

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
    public void testMovieZeroIsAvailableWhenMoviesAreInLibrary() {
        assertTrue(library.movieIsAvailable(0));
    }

    @Test
    public void testMovieZeroIsNotAvailableWhenNoMoviesAreInLibrary() {
        library.setAvailableMovies(buildListOfMockMoviesWithSetSize(0));
        assertFalse(library.movieIsAvailable(0));
    }

    // Checkout Movies

    @Test
    public void testMovieZeroIsCheckedOutWhenMoviesAreCheckedOutOfLibrary() {
        assertTrue(library.bookIsAvailable(0));
    }

    @Test
    public void testValidCheckoutMovieOptionRemovesMovieFromList() {
        library.checkOutMovie(0);
        assertEquals(4, library.numberOfMoviesInLibrary());
    }

    @Test
    public void testInvalidCheckoutMovieOptionDoesNotRemoveMovieFromList() {
        library.checkOutMovie(54398);
        assertEquals(5, library.numberOfMoviesInLibrary());
    }

    // User Login

    @Test
    public void testValidLoginWhenLibraryNumberAndPasswordMatch() {
        assertTrue(library.validUserLogin("123-4567", "myPassword"));
    }


    @Test
    public void testInValidLoginWhenLibraryNumberMatchesAndPasswordDoesNotMatch() {
        assertFalse(library.validUserLogin("123-4567", "&*^%&*^%*&*"));
    }


    @Test
    public void testInValidLoginWhenLibraryNumberDoesNotMatchAndPasswordDoes() {
        assertFalse(library.validUserLogin("123-4588", "myPassword"));
    }

}