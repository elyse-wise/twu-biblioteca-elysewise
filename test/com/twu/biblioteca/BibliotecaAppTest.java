package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;

import java.util.*;

import static org.mockito.Mockito.*;

/**
 * Created by Elyse on 16/02/2016.
 */
public class BibliotecaAppTest {

    private BibliotecaApp application;
    private Console console;
    private Library library;
    private List<MenuOperation> allMenuOptions;
    private List<MenuOperation> menuOptionsWithNoLoginRequired;

    @Before
    public void setup() {
        console = mock(Console.class);
        library = buildMockLibraryWithSetBookAmounts(3, 4);
        allMenuOptions = new ArrayList<MenuOperation>();
        menuOptionsWithNoLoginRequired = new ArrayList<MenuOperation>();

        MenuOperation op = mock(ListBooksMenuOperation.class);
        when(op.isTriggeredBy("l")).thenReturn(true);
        when(op.isTriggeredBy("L")).thenReturn(true);
        allMenuOptions.add(op);
        menuOptionsWithNoLoginRequired.add(op);

        op = mock(QuitMenuOperation.class);
        when(op.isTriggeredBy("q")).thenReturn(true);
        when(op.isTriggeredBy("Q")).thenReturn(true);
        allMenuOptions.add(op);
        menuOptionsWithNoLoginRequired.add(op);


        op = mock(MenuOperation.class);
        when(op.needsLogin()).thenReturn(false);
        allMenuOptions.add(op);
        menuOptionsWithNoLoginRequired.add(op);

        op = mock(MenuOperation.class);
        when(op.needsLogin()).thenReturn(true);
        allMenuOptions.add(op);

        application = new BibliotecaApp(console, library, allMenuOptions);
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
        verify(console).printMenuOptions(menuOptionsWithNoLoginRequired);
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
        verify(console, times(4)).promptUserForMenuOption();
    }

    @Test
    public void testShowsMenuOperationsThatDoNotRequireLoginWhenUserIsNotLoggedIn() {
        when(library.userLoggedIn()).thenReturn(false);
        String commands[] = {"Q"};
        setUserInput(commands);

        application.run();
        verify(console).printMenuOptions(menuOptionsWithNoLoginRequired);
    }

    @Test
    public void testShowsMenuOperationsThatDoNotRequireLoginWhenUserIsLoggedIn() {
        when(library.userLoggedIn()).thenReturn(true);
        String commands[] = {"Q"};
        setUserInput(commands);

        application.run();
        verify(console).printMenuOptions(allMenuOptions);
    }

    @Test
    public void testDoesNotShowMenuOperationsThatRequireLoginWhenUserIsNotLoggedIn() {
        when(library.userLoggedIn()).thenReturn(false);
        String commands[] = {"Q"};
        setUserInput(commands);

        application.run();
        verify(console,never()).printMenuOptions(allMenuOptions);
    }

    private void setUserInput(String inputs[]) {
        List<String> inputList = Arrays.asList(inputs);
        when(console.getUserCommand()).thenAnswer(AdditionalAnswers.returnsElementsOf(inputList));
    }



}