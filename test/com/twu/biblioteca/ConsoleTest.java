package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ConsoleTest {

    private InputStream in;
    private PrintStream out;
    private Console console;

    @Before
    public void setUpStreams() {
        in = mock(InputStream.class);
        out = mock(PrintStream.class);
        console = new Console(in, out);
    }

    @Test
    public void testWelcomeMessageIsDisplayed() {
        console.printWelcome();
        verify(out).println("Welcome to Biblioteca!");
    }

    @Test
    public void testExitMessageIsDisplayed() {
        console.printExitMessage();
        verify(out).println("Exiting Biblioteca...");
    }

    @Test
    public void testHeaderIsDisplayedOnListBooks() {
        console.printListItemsHeader();
        verify(out).println("Books Currently In Library:");
    }

    @Test
    public void testHeaderIsDisplayedOnCheckoutBooks() {
        console.printCheckoutItemsHeader();
        verify(out).println("Books Available For Checkout:");
    }

    @Test
    public void testHeaderIsDisplayedOnReturnBooks() {
        console.printReturnBooksHeader();
        verify(out).println("Books Available For Return:");
    }

    @Test
    public void testNoBooksArePrintedWhenBookListIsEmpty() {
        console.printLibraryItemList(new ArrayList<Book>());
        verify(out, never()).println();
    }

    @Test
    public void testOneBookIsPrintedWhenBookListHasOneBook() {
        List<Book> books = new ArrayList<Book>();
        Book testBook = mock(Book.class);
        books.add(testBook);

        console.printLibraryItemList(books);
        verify(testBook, times(1)).getDetailsString();
    }

    @Test
    public void testAllBooksPrintedWhenBookListHasMultipleBooks() {
        List<Book> books = new ArrayList<Book>();
        Book testBook1 = mock(Book.class);
        Book testBook2 = mock(Book.class);
        Book testBook3 = mock(Book.class);
        books.add(testBook1);
        books.add(testBook2);
        books.add(testBook3);

        console.printLibraryItemList(books);
        verify(testBook1, times(1)).getDetailsString();
        verify(testBook2, times(1)).getDetailsString();
        verify(testBook3, times(1)).getDetailsString();
    }

    @Test
    public void testColumnsArePrintedWhenShowingBookList() {
        List<Book> books = new ArrayList<Book>();
        Book testBook = mock(Book.class);
        books.add(testBook);

        console.printLibraryItemList(books);
        verify(testBook, times(1)).getColumnString();
    }

    @Test
    public void testNoMoviesArePrintedWhenMovieListIsEmpty() {
        console.printLibraryItemList(new ArrayList<Movie>());
        verify(out, never()).println();
    }

    @Test
    public void testOneMovieIsPrintedWhenMovieListHasOneBook() {
        List<Movie> movies = new ArrayList<Movie>();
        Movie movie = mock(Movie.class);
        movies.add(movie);

        console.printLibraryItemList(movies);
        verify(movie, times(1)).getDetailsString();
    }

    @Test
    public void testAllMoviesPrintedWhenMovieListHasMultipleMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        Movie testMovie1 = mock(Movie.class);
        Movie testMovie2 = mock(Movie.class);
        Movie testMovie3 = mock(Movie.class);
        movies.add(testMovie1);
        movies.add(testMovie2);
        movies.add(testMovie3);

        console.printLibraryItemList(movies);
        verify(testMovie1, times(1)).getDetailsString();
        verify(testMovie2, times(1)).getDetailsString();
        verify(testMovie3, times(1)).getDetailsString();
    }

    @Test
    public void testColumnsArePrintedWhenShowingMovieList() {
        List<Movie> movies= new ArrayList<Movie>();
        Movie testMovie = mock(Movie.class);
        movies.add(testMovie);

        console.printLibraryItemList(movies);
        verify(testMovie, times(1)).getColumnString();
    }

    @Test
    public void testMenuDisplayHasListBooksOption() {
        List<MenuOperation> menuOperations = new ArrayList<MenuOperation>();
        MenuOperation op = mock(MenuOperation.class);
        when(op.getDescription()).thenReturn("List Books");
        menuOperations.add(op);

        console.printMenuOptions(menuOperations);
        verify(out).println("List Books");
    }

    @Test
    public void testWarnInvalidMenuOption() {
        console.warnInvalidMenuOption();
        verify(out).println("Select a valid option!");
    }

    @Test
    public void testWarnInvalidBookSelectionForCheckout() {
        console.warnInvalidBookSelectionForCheckout();
        verify(out).println("That book is not available");
    }

    @Test
    public void testThankUserForCheckingOut() {
        console.thankUserForCheckingOutBook();
        verify(out).println("Thank you! Enjoy the book");
    }

    @Test
    public void testWarnInvalidBookSelectionForReturn() {
        console.warnInvalidBookSelectionForReturn();
        verify(out).println("That is not a valid book to return");
    }

    @Test
    public void testThankUserForReturningBook() {
        console.thankUserForReturningBook();
        verify(out).println("Thank you for returning the book");
    }
}
