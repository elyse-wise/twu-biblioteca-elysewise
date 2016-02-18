package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

/**
 * Created by Elyse on 17/02/2016.
 */
public class ReturnBookMenuOperationTest {

    private ReturnBookMenuOperation returnBookMenuOperation;
    private Library library;
    private Console console;

    @Before
    public void setup() {
        this.returnBookMenuOperation = new ReturnBookMenuOperation("R", "Return Book");
        this.library = buildMockLibraryWithSetBookAmounts(3, 4);
        this.console = mock(Console.class);
    }

    private Library buildMockLibraryWithSetBookAmounts(int numberOfBooksAvailable, int numberOfBooksCheckedOut) {
        library = mock(Library.class);
        when(library.numberOfBooksInLibrary()).thenReturn(numberOfBooksAvailable);
        when(library.numberOfBooksCheckedOut()).thenReturn(numberOfBooksCheckedOut);
        for (int i = 0; i < numberOfBooksAvailable; i++) {
            when(library.bookIsAvailable(i)).thenReturn(true);
        }
        for (int i = 0; i < numberOfBooksCheckedOut; i++) {
            when(library.bookIsCheckedOut(i)).thenReturn(true);
        }
        return library;
    }

    @Test
    public void testReturnBookIsTriggeredByLowerCaseR() {
        assertTrue(returnBookMenuOperation.isTriggeredBy("r"));
    }

    @Test
    public void testReturnBookIsTriggeredByUpperCaseR() {
        assertTrue(returnBookMenuOperation.isTriggeredBy("R"));
    }

    @Test
    public void testReturnBooksHeaderPrintedOnExecute() {
        returnBookMenuOperation.execute(library, console);
        verify(console).printReturnBooksHeader();
    }

    @Test
    public void testCheckedOutBookListPrintedOnExecute() {
        returnBookMenuOperation.execute(library, console);
        verify(console).printLibraryItemList(library.checkedOutBooks());
    }

    @Test
    public void testUserPromptedForBookSelectionOnExecute() {
        returnBookMenuOperation.execute(library, console);
        verify(console).promptUserForItemSelection();
    }

    @Test
    public void testValidReturnBookOptionReturnsBookToLibrary() {
        String commands[] = {"1"};
        setUserInput(commands);

        returnBookMenuOperation.execute(library, console);
        verify(console, never()).warnInvalidMenuOption();
        verify(library).returnBook(1);
    }

    @Test
    public void testValidReturnBookOptionGivesUserThankYouMessage() {
        String commands[] = {"1"};
        setUserInput(commands);

        returnBookMenuOperation.execute(library, console);
        verify(console, never()).warnInvalidMenuOption();
        verify(console, times(1)).thankUserForReturningBook();
    }

    @Test
    public void testInvalidReturnBookOptionWarnsUser() {
        String commands[] = {"-23"};
        setUserInput(commands);

        returnBookMenuOperation.execute(library, console);
        verify(console, times(1)).warnInvalidBookSelectionForReturn();
    }

    @Test
    public void testInvalidReturnBookOptionDoesNotReturnBook() {
        String commands[] = {"193738"};
        setUserInput(commands);

        returnBookMenuOperation.execute(library, console);
        verify(console, never()).warnInvalidMenuOption();
        verify(library, never()).returnBook(193738);
    }

    @Test
    public void testNegativeInvalidReturnBookOptionDoesNotReturnBook() {
        String commands[] = {"-23"};
        setUserInput(commands);

        returnBookMenuOperation.execute(library, console);
        verify(console, never()).warnInvalidMenuOption();
        verify(library, never()).returnBook(-23);
    }

    private void setUserInput(String inputs[]) {
        List<String> inputList = Arrays.asList(inputs);
        when(console.getUserCommand()).thenAnswer(AdditionalAnswers.returnsElementsOf(inputList));
    }
}