package com.twu.biblioteca;


import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConsoleTest {

    @Test
    public void testWelcomeMessageIsDisplayed() {
        Console console = new Console();
        PrintStream out = mock(PrintStream.class);

        console.printWelcome(out);
        verify(out).println("Welcome to Biblioteca!");
    }
}
