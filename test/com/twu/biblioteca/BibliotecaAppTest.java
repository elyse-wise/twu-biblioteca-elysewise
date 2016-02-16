package com.twu.biblioteca;

import com.sun.source.tree.AssertTree;
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
    private List<Book> books;
    private Map<String, String> menuOptions;

    @Before
    public void setup() {
        console = mock(Console.class);
        books = new ArrayList<Book>();
        menuOptions = new HashMap<String, String>();
        menuOptions.put("l", "List Books");
        application = new BibliotecaApp(console, books, menuOptions);
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
        verify(console).printBookList(books);
    }

    @Test
    public void testBookListIsNotDisplayedWhenUserEntersSomethingElse() {
        String commands[] = {"lamp", "Q"};
        setUserInput(commands);

        application.run();
        verify(console, never()).printBookList(books);
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
        verify(console, times(3)).printBookList(books);
        verify(console, times(4)).promptUser();
    }

    @Test
    public void testApplicationIgnoresCaseForUserCommands() {
        String commands[] = {"l", "L", "l", "q"};
        setUserInput(commands);

        application.run();
        verify(console, never()).warnInvalidMenuOption();
        verify(console, times(3)).printBookList(books);
    }

    @Test
    public void numberOfBooksInLibraryReturnsZeroForEmptyBookList() {
        assertEquals(0, application.numberOfBooksInLibrary());
    }

    @Test
    public void numberOfBooksInLibraryReturnsThreeForBookListOfThree() {

        //add 3 books to the library
        books.add(mock(Book.class));
        books.add(mock(Book.class));
        books.add(mock(Book.class));

        application = new BibliotecaApp(console, books, menuOptions);
        assertEquals(3, application.numberOfBooksInLibrary());
    }

    @Test
    public void testValidCheckoutBookOptionRemovesBookFromList() {

        //add 2 books to the library
        books.add(mock(Book.class));
        books.add(mock(Book.class));
        application = new BibliotecaApp(console, books, menuOptions);

        String commands[] = {"C", "1", "q"};
        setUserInput(commands);

        application.run();
        verify(console, never()).warnInvalidMenuOption();
        assertEquals(1, application.numberOfBooksInLibrary());
    }


    @Test
    public void testInvalidCheckoutBookOptionDoesNotRemoveBookFromList() {

        //add 2 books to the library
        books.add(mock(Book.class));
        books.add(mock(Book.class));
        application = new BibliotecaApp(console, books, menuOptions);

        String commands[] = {"C", "193738", "q"};
        setUserInput(commands);

        application.run();
        verify(console, never()).warnInvalidMenuOption();
        assertEquals(2, application.numberOfBooksInLibrary());
    }

    @Test
    public void testNegativeInvalidCheckoutBookOptionDoesNotRemoveBookFromList() {

        //add 2 books to the library
        books.add(mock(Book.class));
        books.add(mock(Book.class));
        application = new BibliotecaApp(console, books, menuOptions);

        String commands[] = {"C", "-23", "q"};
        setUserInput(commands);

        application.run();
        verify(console, never()).warnInvalidMenuOption();
        assertEquals(2, application.numberOfBooksInLibrary());
    }

    private void setUserInput(String inputs[]) {
        List<String> inputList = Arrays.asList(inputs);
        when(console.getUserCommand()).thenAnswer(AdditionalAnswers.returnsElementsOf(inputList));
    }


}