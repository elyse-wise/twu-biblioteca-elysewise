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
public class CheckoutBookMenuOperationTest {

    private CheckoutBookMenuOperation checkoutBookMenuOperation;
    private Library library;
    private Console console;

    @Before
    public void setup() {
        this.checkoutBookMenuOperation = new CheckoutBookMenuOperation("C", "Checkout Books");
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
    public void testCheckoutBookIsTriggeredByLowerCaseC() {
        assertTrue(checkoutBookMenuOperation.isTriggeredBy("c"));
    }

    @Test
    public void testCheckoutBookIsTriggeredByUpperCaseC() {
        assertTrue(checkoutBookMenuOperation.isTriggeredBy("C"));
    }

    @Test
    public void testCheckoutBooksHeaderPrintedOnExecute() {
        checkoutBookMenuOperation.execute(library, console);
        verify(console).printMessage("Books available for checkout:");
    }

    @Test
    public void testAvailableBookListPrintedOnExecute() {
        checkoutBookMenuOperation.execute(library, console);
        verify(console).printLibraryItemList(library.availableBooks());
    }

    @Test
    public void testUserPromptedForBookSelectionOnExecute() {
        checkoutBookMenuOperation.execute(library, console);
        verify(console).printMessage("> Enter book number: ");
    }


    @Test
    public void testValidCheckoutBookOptionRemovesBookFromList() {
        String commands[] = {"1"};
        setUserInput(commands);

        checkoutBookMenuOperation.execute(library, console);
        verify(library).checkOutBook(1);
    }

    @Test
    public void testValidCheckoutBookOptionGivesUserThankYouMessage() {
        String commands[] = {"1"};
        setUserInput(commands);

        checkoutBookMenuOperation.execute(library, console);
        verify(console).printMessage("Thank you! Enjoy the book");
    }

    @Test
    public void testInvalidCheckoutBookOptionWarnsUser() {
        String commands[] = {"-23"};
        setUserInput(commands);

        checkoutBookMenuOperation.execute(library, console);
        verify(console).printMessage("That book is not available");
    }

    @Test
    public void testInvalidCheckoutBookOptionDoesNotRemoveBookFromList() {
        String commands[] = {"193738"};
        setUserInput(commands);

        checkoutBookMenuOperation.execute(library, console);
        verify(library, never()).checkOutBook(193738);
    }

    @Test
    public void testNegativeInvalidCheckoutBookOptionDoesNotRemoveBookFromList() {
        String commands[] = {"-23"};
        setUserInput(commands);

        checkoutBookMenuOperation.execute(library, console);
        verify(library, never()).checkOutBook(-23);
    }

    @Test
    public void testUserNeedsToBeLoggedIn() {
        assertTrue(checkoutBookMenuOperation.needsLogin());
    }

    private void setUserInput(String inputs[]) {
        List<String> inputList = Arrays.asList(inputs);
        when(console.getUserCommand()).thenAnswer(AdditionalAnswers.returnsElementsOf(inputList));
    }
}