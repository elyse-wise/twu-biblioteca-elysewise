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
    private List<MenuOperation> menuOptions;

    @Before
    public void setup() {
        console = mock(Console.class);
        library = buildMockLibraryWithSetBookAmounts(3, 4);
        menuOptions = new ArrayList<MenuOperation>();

        MenuOperation listOp = mock(ListBooksMenuOperation.class);
        when(listOp.isTriggeredBy("l")).thenReturn(true);
        when(listOp.isTriggeredBy("L")).thenReturn(true);

        MenuOperation quitOp = mock(QuitMenuOperation.class);
        when(quitOp.isTriggeredBy("q")).thenReturn(true);
        when(quitOp.isTriggeredBy("Q")).thenReturn(true);

        menuOptions.add(listOp);
        menuOptions.add(quitOp);

        application = new BibliotecaApp(console, library, menuOptions);
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
        verify(console).printMenuOptions(menuOptions);
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

    private void setUserInput(String inputs[]) {
        List<String> inputList = Arrays.asList(inputs);
        when(console.getUserCommand()).thenAnswer(AdditionalAnswers.returnsElementsOf(inputList));
    }


}