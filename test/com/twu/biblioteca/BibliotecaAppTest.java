package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        application.run();
        verify(console).printWelcome();
    }

    @Test
    public void testMenuAppearsOnRun() {
        application.run();
        verify(console).printMenuOptions(menuOptions);
    }

    @Test
    public void testBookListIsDisplayedWhenUserEntersL() {
        setUserInput("l");
        application.run();
        verify(console).printBookList(books);
    }

    @Test
    public void testBookListIsNotDisplayedWhenUserEntersSomethingElse() {
        setUserInput("lamp");
        application.run();
        verify(console, never()).printBookList(books);
    }

    private void setUserInput(String input) {
        when(console.getUserCommand()).thenReturn(input);
    }
}