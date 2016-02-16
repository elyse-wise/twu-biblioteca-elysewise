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

    private void setUserInput(String inputs[]) {
        List<String> inputList = Arrays.asList(inputs);
        when(console.getUserCommand()).thenAnswer(AdditionalAnswers.returnsElementsOf(inputList));
    }


}