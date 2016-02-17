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
        Library library = mock(Library.class);
        menuOptions = new HashMap<String, String>();
        menuOptions.put("l", "List Books");
        application = new BibliotecaApp(console, library, menuOptions);
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

        //add 2 books to the library
        List<Book> books = new ArrayList<Book>();
        books.add(mock(Book.class));
        books.add(mock(Book.class));
        library = new Library(books);
        application = new BibliotecaApp(console, library, menuOptions);

        String commands[] = {"C", "1", "q"};
        setUserInput(commands);

        application.run();
        verify(console, never()).warnInvalidMenuOption();
        assertEquals(1, library.numberOfBooksInLibrary());
    }

    @Test
    public void testValidCheckoutBookOptionGivesUserThankYouMessage() {

        //add 2 books to the library
        List<Book> books = new ArrayList<Book>();
        books.add(mock(Book.class));
        books.add(mock(Book.class));
        library = new Library(books);
        application = new BibliotecaApp(console, library, menuOptions);

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

        //add 2 books to the library
        List<Book> books = new ArrayList<Book>();
        books.add(mock(Book.class));
        books.add(mock(Book.class));
        library = new Library(books);
        application = new BibliotecaApp(console, library, menuOptions);

        String commands[] = {"C", "193738", "q"};
        setUserInput(commands);

        application.run();
        verify(console, never()).warnInvalidMenuOption();
        assertEquals(2, library.numberOfBooksInLibrary());
    }

    @Test
    public void testNegativeInvalidCheckoutBookOptionDoesNotRemoveBookFromList() {

        //add 2 books to the library
        List<Book> books = new ArrayList<Book>();
        books.add(mock(Book.class));
        books.add(mock(Book.class));
        library = new Library(books);
        application = new BibliotecaApp(console, library, menuOptions);

        String commands[] = {"C", "-23", "q"};
        setUserInput(commands);

        application.run();
        verify(console, never()).warnInvalidMenuOption();
        assertEquals(2, library.numberOfBooksInLibrary());
    }

    private void setUserInput(String inputs[]) {
        List<String> inputList = Arrays.asList(inputs);
        when(console.getUserCommand()).thenAnswer(AdditionalAnswers.returnsElementsOf(inputList));
    }


}