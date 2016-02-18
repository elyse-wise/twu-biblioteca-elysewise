package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Elyse on 17/02/2016.
 */
public class ListBooksMenuOperationTest {

    private ListBooksMenuOperation listBooksMenuOperation;
    private Library library;
    private Console console;

    @Before
    public void setup() {
        this.listBooksMenuOperation = new ListBooksMenuOperation("L", "ListBooks");
        this.library = mock(Library.class);
        this.console = mock(Console.class);
    }

    @Test
    public void testListBooksIsTriggeredByLowerCaseL() {
        assertTrue(listBooksMenuOperation.isTriggeredBy("l"));
    }

    @Test
    public void testListBooksIsTriggeredByUpperCaseL() {
        assertTrue(listBooksMenuOperation.isTriggeredBy("L"));
    }

    @Test
    public void testListBooksHeaderPrintedOnExecute() {
        listBooksMenuOperation.execute(library, console);
        verify(console).printMessage("Books Currently In Library:");
    }

    @Test
    public void testBookListPrintedOnExecute() {
        listBooksMenuOperation.execute(library, console);
        verify(console).printLibraryItemList(library.availableBooks());
    }
}