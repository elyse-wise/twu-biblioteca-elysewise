package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by Elyse on 16/02/2016.
 */
public class BibliotecaAppTest {

    private BibliotecaApp application;
    private Console console;
    private Library library;
    private Map<String, String> menuOptions;

    @Before
    public void setup() {
        console = mock(Console.class);
        library = buildMockLibraryWithSetBookAmount(3);
        menuOptions = new HashMap<String, String>();
        menuOptions.put("l", "List Books");
        application = new BibliotecaApp(console, library, menuOptions);
    }

    private Library buildMockLibraryWithSetBookAmount(int amountOfBooks) {
        library = mock(Library.class);
        when(library.numberOfBooksInLibrary()).thenReturn(amountOfBooks);
        for (int i = 0; i < amountOfBooks; i++) {
            when(library.bookIsAvailable(i)).thenReturn(true);
        }
        return library;
    }

    @Test
    public void testWelcomeAppearsOnRun() {
        String commands[] = {"Q"};
        setUserInput(commands);

        application.run();
        verify(console).printWelcome();
    }

    @Test
    public void testMenuAppearsOnRun() {
        String commands[] = {"Q"};
        setUserInput(commands);

        application.run();
        verify(console).printMenuOptions(menuOptions);
    }

    @Test
    public void testBookListIsDisplayedWhenUserEntersL() {
        String commands[] = {"l", "Q"};
        setUserInput(commands);

        application.run();
        verify(console).printBookList(library.availableBooks());
    }

    @Test
    public void testBookListIsNotDisplayedWhenUserEntersSomethingElse() {
        String commands[] = {"lamp", "Q"};
        setUserInput(commands);

        application.run();
        verify(console, never()).printBookList(library.availableBooks());
    }

    @Test
    public void testShowsInvalidOptionIfUserEntersUnknownCommand() {
        String commands[] = {"(*&$&*^$(&#$^(#&", "Q"};
        setUserInput(commands);

        application.run();
        verify(console, times(1)).warnInvalidMenuOption();
    }

    @Test
    public void testShowsInvalidOptionIfUserEntersEmptyString() {
        String commands[] = {"", "Q"};
        setUserInput(commands);

        application.run();
        verify(console, times(1)).warnInvalidMenuOption();
    }

    @Test
    public void testApplicationLeavesMenuWhenUserEntersQ() {
        String commands[] = {"l", "l", "l", "Q"};
        setUserInput(commands);

        application.run();
        verify(console, times(3)).printBookList(library.availableBooks());
        verify(console, times(4)).promptUserForMenuOption();
    }

    @Test
    public void testApplicationIgnoresCaseForUserCommands() {
        String commands[] = {"l", "L", "l", "q"};
        setUserInput(commands);

        application.run();
        verify(console, never()).warnInvalidMenuOption();
        verify(console, times(3)).printBookList(library.availableBooks());
    }

    @Test
    public void testValidCheckoutBookOptionRemovesBookFromList() {
        String commands[] = {"C", "1", "q"};
        setUserInput(commands);

        application.run();
        verify(console, never()).warnInvalidMenuOption();
        verify(library).checkOutBook(1);
    }

    @Test
    public void testValidCheckoutBookOptionGivesUserThankYouMessage() {
        String commands[] = {"C", "1", "q"};
        setUserInput(commands);

        application.run();
        verify(console, never()).warnInvalidMenuOption();
        verify(console, times(1)).thankUserForCheckingOut();
    }

    @Test
    public void testInvalidCheckoutBookOptionWarnsUser() {
        String commands[] = {"C", "-23", "q"};
        setUserInput(commands);

        application.run();
        verify(console, times(1)).warnInvalidBookSelection();
    }

    @Test
    public void testInvalidCheckoutBookOptionDoesNotRemoveBookFromList() {
        String commands[] = {"C", "193738", "q"};
        setUserInput(commands);

        application.run();
        verify(console, never()).warnInvalidMenuOption();
        verify(library, never()).checkOutBook(193738);
    }

    @Test
    public void testNegativeInvalidCheckoutBookOptionDoesNotRemoveBookFromList() {
        String commands[] = {"C", "-23", "q"};
        setUserInput(commands);

        application.run();
        verify(console, never()).warnInvalidMenuOption();
        verify(library, never()).checkOutBook(-23);
    }

    private void setUserInput(String inputs[]) {
        List<String> inputList = Arrays.asList(inputs);
        when(console.getUserCommand()).thenAnswer(AdditionalAnswers.returnsElementsOf(inputList));
    }


}