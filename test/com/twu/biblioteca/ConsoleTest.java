package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ConsoleTest {

    private PrintStream out;
    private Console console;

    @Before
    public void setUpStreams() {
        out = mock(PrintStream.class);
        console = new Console(out);
    }


    @Test
    public void testWelcomeMessageIsDisplayed() {
        console.printWelcome();
        verify(out).println("Welcome to Biblioteca!");
    }

    @Test
    public void testNoBooksArePrintedWhenLibraryIsEmpty() {
        List<Book> books = new ArrayList<Book>();

        console.printBookList(books);
        verify(out, never()).println();
    }

    @Test
    public void testOneBookIsPrintedWhenOneBookIsInLibrary() {
        List<Book> books = new ArrayList<Book>();
        Book testBook = mock(Book.class);
        books.add(testBook);

        console.printBookList(books);
        verify(testBook, times(1)).getDetailsString();
    }

    @Test
    public void testAllBooksPrintedWhenMultipleBooksAreInLibrary() {
        List<Book> books = new ArrayList<Book>();
        Book testBook1 = mock(Book.class);
        Book testBook2 = mock(Book.class);
        Book testBook3 = mock(Book.class);
        books.add(testBook1);
        books.add(testBook2);
        books.add(testBook3);

        console.printBookList(books);
        verify(testBook1, times(1)).getDetailsString();
        verify(testBook2, times(1)).getDetailsString();
        verify(testBook3, times(1)).getDetailsString();
    }

    @Test
    public void testColumnsArePrintedWhenShowingBookList() {
        List<Book> books = new ArrayList<Book>();
        Book testBook = mock(Book.class);
        books.add(testBook);

        console.printBookList(books);
        verify(testBook, times(1)).getColumnString();
    }

    @Test
    public void testMenuDisplayHasListBooksOption() {
        console.printMenuOptions();
        verify(out).println("List Books");
    }
}
