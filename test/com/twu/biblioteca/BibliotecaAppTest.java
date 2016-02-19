package com.twu.biblioteca;

import com.sun.glass.ui.Menu;
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
    private List<MenuOperation> menuOperations;


    @Before
    public void setup() {
        console = mock(Console.class);
        library = buildMockLibraryWithSetBookAmounts(3, 4);
        menuOperations = new ArrayList<MenuOperation>();

        menuOperations.add(buildMockListBooksMenuOperation());
        menuOperations.add(buildMockQuitMenuOperation());
        menuOperations.add(buildMockMenuOperationWithAccessControl(true, false));
        menuOperations.add(buildMockMenuOperationWithAccessControl(true, true));

        application = new BibliotecaApp(console, library, menuOperations);
    }

    private MenuOperation buildMockListBooksMenuOperation() {
        MenuOperation op = mock(ListBooksMenuOperation.class);
        when(op.needsLogin()).thenReturn(false);
        when(op.needsAdministratorLogin()).thenReturn(false);
        when(op.isTriggeredBy("l")).thenReturn(true);
        when(op.isTriggeredBy("L")).thenReturn(true);
        return op;
    }

    private MenuOperation buildMockQuitMenuOperation() {
        MenuOperation op = mock(QuitMenuOperation.class);
        when(op.needsLogin()).thenReturn(false);
        when(op.needsAdministratorLogin()).thenReturn(false);
        when(op.isTriggeredBy("q")).thenReturn(true);
        when(op.isTriggeredBy("Q")).thenReturn(true);
        return op;
    }

    private MenuOperation buildMockMenuOperationWithAccessControl(Boolean loginRequired, Boolean adminRequired) {
        MenuOperation op = mock(MenuOperation.class);
        when(op.needsLogin()).thenReturn(loginRequired);
        when(op.needsAdministratorLogin()).thenReturn(adminRequired);
        return op;
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

    private List<MenuOperation> menuOptionsWithNoLoginRequired() {
        List<MenuOperation> operations = new ArrayList<MenuOperation>();
        for (MenuOperation op : menuOperations) {
            if (!op.needsLogin())
                operations.add(op);
        }
        return operations;
    }

    private List<MenuOperation> menuOptionsWithNoAdminRequired() {
        List<MenuOperation> operations = new ArrayList<MenuOperation>();
        for (MenuOperation op : menuOperations) {
            if (!op.needsAdministratorLogin())
                operations.add(op);
        }
        return operations;
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
        verify(console).printMenuOptions(menuOptionsWithNoLoginRequired());
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
        verify(console).printMenuOptions(menuOptionsWithNoLoginRequired());
    }

    @Test
    public void testShowsMenuOperationsThatDoNotRequireLoginWhenUserIsLoggedIn() {
        when(library.userLoggedIn()).thenReturn(true);
        String commands[] = {"Q"};
        setUserInput(commands);

        application.run();
        verify(console).printMenuOptions(menuOptionsWithNoAdminRequired());
    }

    @Test
    public void testDoesNotShowMenuOperationsThatRequireLoginWhenUserIsNotLoggedIn() {
        when(library.userLoggedIn()).thenReturn(false);
        String commands[] = {"Q"};
        setUserInput(commands);

        application.run();
        verify(console, never()).printMenuOptions(menuOperations);
    }

    @Test
    public void testDoesNotShowMenuOperationsThatRequireAdminWhenRegularUserIsLoggedIn() {
        when(library.userLoggedIn()).thenReturn(true);
        when(library.administratorLoggedIn()).thenReturn(false);

        String commands[] = {"Q"};
        setUserInput(commands);

        application.run();
        verify(console, never()).printMenuOptions(menuOperations);
    }

    @Test
    public void testShowsMenuOperationsThatRequireAdminWhenAdminUserIsLoggedIn() {
        when(library.userLoggedIn()).thenReturn(true);
        when(library.administratorLoggedIn()).thenReturn(true);

        String commands[] = {"Q"};
        setUserInput(commands);

        application.run();
        verify(console).printMenuOptions(menuOperations);
    }


    private void setUserInput(String inputs[]) {
        List<String> inputList = Arrays.asList(inputs);
        when(console.getUserCommand()).thenAnswer(AdditionalAnswers.returnsElementsOf(inputList));
    }



}