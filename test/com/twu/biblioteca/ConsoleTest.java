package com.twu.biblioteca;


import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ConsoleTest {

    @Test
    public void testWelcomeMessageIsDisplayed() {
        Console console = new Console();
        PrintStream out = mock(PrintStream.class);

        console.printWelcome(out);
        verify(out).println("Welcome to Biblioteca!");
    }

    @Test
    public void testNoBooksArePrintedWhenLibraryIsEmpty() {
        Console console = new Console();
        PrintStream out = mock(PrintStream.class);

        List<String> books = new ArrayList<String>();

        console.printBookList(books, out);
        verify(out, never()).println();
    }

    @Test
    public void testOneBookIsPrintedWhenOneBookIsInLibrary() {
        Console console = new Console();
        PrintStream out = mock(PrintStream.class);

        List<String> books = new ArrayList<String>();
        books.add("A Tale of Two Cities");

        console.printBookList(books, out);
        verify(out, times(1)).println("A Tale of Two Cities");
    }

    @Test
    public void testAllBooksPrintedWhenMultipleBooksAreInLibrary() {
        Console console = new Console();
        PrintStream out = mock(PrintStream.class);

        List<String> books = new ArrayList<String>();
        books.add("A Tale of Two Cities");
        books.add("More Tales about Cities");
        books.add("Head First Java");

        console.printBookList(books, out);
        verify(out, times(1)).println("A Tale of Two Cities");
        verify(out, times(1)).println("More Tales about Cities");
        verify(out, times(1)).println("Head First Java");

    }

}
