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
    public void testOneBookIsPrintedWhenOneBookIsInLibrary() {
        Console console = new Console();
        PrintStream out = mock(PrintStream.class);

        List<String> books = new ArrayList<String>();
        books.add("A Tale of Two Cities");

        console.printBookList(books, out);
        verify(out, times(1)).println("A Tale of Two Cities");
    }


}
