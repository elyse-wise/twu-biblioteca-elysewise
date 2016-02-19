package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Elyse on 19/02/2016.
 */
public class ListCheckedOutBooksMenuOperationTest {

    private ListCheckedOutBooksMenuOperation listCheckedOutBooksMenuOperation;
    private Library library;
    private Console console;

    @Before
    public void setup() {
        listCheckedOutBooksMenuOperation = new ListCheckedOutBooksMenuOperation("LCB", "List checked out books");
        library = mock(Library.class);
        console = mock(Console.class);
    }

    @Test
    public void testCheckedOutBookInformationPrintedOnExecute() {
        listCheckedOutBooksMenuOperation.execute(library, console);
        verify(console).printCheckedOutBooksBorrowerInformation(library.checkedOutBooks);
    }

    @Test
    public void testListBooksIsTriggeredByLowerCaseL() {
        assertTrue(listCheckedOutBooksMenuOperation.isTriggeredBy("lcb"));
    }

    @Test
    public void testListBooksIsTriggeredByUpperCaseL() {
        assertTrue(listCheckedOutBooksMenuOperation.isTriggeredBy("LCB"));
    }

    @Test
    public void testListBooksHeaderPrintedOnExecute() {
        listCheckedOutBooksMenuOperation.execute(library, console);
        verify(console).printMessage("Books Currently Checked Out:");
    }

    @Test
    public void testUserNeedsToBeLoggedIn() {
        assertTrue(listCheckedOutBooksMenuOperation.needsLogin());
    }

    @Test
    public void testAdministratorNeedsToBeLoggedIn() {
        assertTrue(listCheckedOutBooksMenuOperation.needsAdministratorLogin());
    }
}