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
        List<String> books = new ArrayList<String>();

        console.printBookList(books);
        verify(out, never()).println();
    }

    @Test
    public void testOneBookIsPrintedWhenOneBookIsInLibrary() {
        List<String> books = new ArrayList<String>();
        books.add("A Tale of Two Cities");

        console.printBookList(books);
        verify(out, times(1)).println("A Tale of Two Cities");
    }

    @Test
    public void testAllBooksPrintedWhenMultipleBooksAreInLibrary() {
        List<String> books = new ArrayList<String>();
        books.add("A Tale of Two Cities");
        books.add("More Tales about Cities");
        books.add("Head First Java");

        console.printBookList(books);
        verify(out, times(1)).println("A Tale of Two Cities");
        verify(out, times(1)).println("More Tales about Cities");
        verify(out, times(1)).println("Head First Java");

    }

}
