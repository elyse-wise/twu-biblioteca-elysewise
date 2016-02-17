package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Elyse on 17/02/2016.
 */
public class QuitMenuOperationTest {

    private QuitMenuOperation quitMenuOperation;
    private Library library;
    private Console console;

    @Before
    public void setup() {
        this.quitMenuOperation = new QuitMenuOperation("Q", "Quit");
        this.library = mock(Library.class);
        this.console = mock(Console.class);
    }

    @Test
    public void testQuitIsTriggeredByLowerCaseQ() {
        assertTrue(quitMenuOperation.isTriggeredBy("q"));
    }

    @Test
    public void testQuitIsTriggeredByUpperCaseQ() {
        assertTrue(quitMenuOperation.isTriggeredBy("Q"));
    }

    @Test
    public void testExitMessagePrintedOnExecute() {
        quitMenuOperation.execute(library, console);
        verify(console).printExitMessage();
    }
}