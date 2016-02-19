package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Elyse on 18/02/2016.
 */
public class CheckoutMovieMenuOperationTest {

    private CheckoutMovieMenuOperation checkoutMovieMenuOperation;
    private Library library;
    private Console console;

    @Before
    public void setup() {
        this.checkoutMovieMenuOperation = new CheckoutMovieMenuOperation("CM", "Checkout Movies");
        this.library = buildMockLibraryWithSetMovieAmounts(3);
        this.console = mock(Console.class);
    }

    private Library buildMockLibraryWithSetMovieAmounts(int numberOfMoviesAvailable) {
        library = mock(Library.class);
        when(library.numberOfMoviesInLibrary()).thenReturn(numberOfMoviesAvailable);
        for (int i = 0; i < numberOfMoviesAvailable; i++) {
            when(library.movieIsAvailable(i)).thenReturn(true);
        }
        return library;
    }

    @Test
    public void testCheckoutMovieIsTriggeredByLowerCaseCM() {
        assertTrue(checkoutMovieMenuOperation.isTriggeredBy("cm"));
    }

    @Test
    public void testCheckoutMovieIsTriggeredByUpperCaseCM() {
        assertTrue(checkoutMovieMenuOperation.isTriggeredBy("CM"));
    }

    @Test
    public void testCheckoutMoviesHeaderPrintedOnExecute() {
        checkoutMovieMenuOperation.execute(library, console);
        verify(console).printMessage("Movies available for checkout:");
    }

    @Test
    public void testAvailableMovieListPrintedOnExecute() {
        checkoutMovieMenuOperation.execute(library, console);
        verify(console).printLibraryItemList(library.availableMovies());
    }

    @Test
    public void testUserPromptedForMovieSelectionOnExecute() {
        checkoutMovieMenuOperation.execute(library, console);
        verify(console).printMessage("> Enter movie number: ");
    }


    @Test
    public void testValidCheckoutMovieOptionGivesUserThankYouMessage() {
        String commands[] = {"1"};
        setUserInput(commands);

        checkoutMovieMenuOperation.execute(library, console);
        verify(console).printMessage("Thank you! Enjoy the movie");
    }

    @Test
    public void testInvalidCheckoutMovieOptionWarnsUser() {
        String commands[] = {"-23"};
        setUserInput(commands);

        checkoutMovieMenuOperation.execute(library, console);
        verify(console).printMessage("That movie is not available");
    }

    @Test
    public void testValidCheckoutMovieOptionRemovesMovieFromList() {
        String commands[] = {"1"};
        setUserInput(commands);

        checkoutMovieMenuOperation.execute(library, console);
        verify(console, never()).warnInvalidMenuOption();
        verify(library).checkOutMovie(1);
    }

    @Test
    public void testInvalidCheckoutMovieOptionDoesNotRemoveMovieFromList() {
        String commands[] = {"193738"};
        setUserInput(commands);

        checkoutMovieMenuOperation.execute(library, console);
        verify(console, never()).warnInvalidMenuOption();
        verify(library, never()).checkOutMovie(193738);
    }

    @Test
    public void testNegativeInvalidCheckoutMovieOptionDoesNotRemoveMovieFromList() {
        String commands[] = {"-23"};
        setUserInput(commands);

        checkoutMovieMenuOperation.execute(library, console);
        verify(console, never()).warnInvalidMenuOption();
        verify(library, never()).checkOutMovie(-23);
    }

    @Test
    public void testWarnsUserIfNoMoviesAreAvailable() {
        when(library.numberOfMoviesInLibrary()).thenReturn(0);

        checkoutMovieMenuOperation.execute(library, console);
        verify(console).printMessage("There are no movies available for checkout");

    }

    @Test
    public void testUserNeedsToBeLoggedIn() {
        assertTrue(checkoutMovieMenuOperation.needsLogin());
    }

    private void setUserInput(String inputs[]) {
        List<String> inputList = Arrays.asList(inputs);
        when(console.getUserCommand()).thenAnswer(AdditionalAnswers.returnsElementsOf(inputList));
    }
}