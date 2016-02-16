package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
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
    private InputStream in;
    private PrintStream out;
    private List<Book> books;
    private Map<String, String> menuOptions;


    @Before
    public void setup() {
        console = mock(Console.class);

        books = new ArrayList<Book>();
        Book book1 = mock(Book.class);
        Book book2 = mock(Book.class);
        books.add(book1);
        books.add(book2);

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

        //emulate selection "l"
        when(console.getUserCommand()).thenReturn("l");

        application.run();
        verify(console).printBookList(books);
    }

    @Test
    public void testBookListIsNotDisplayedWhenUserEntersSomethingElse() {
        when(console.getUserCommand()).thenReturn("lamp");

        application.run();
        verify(console, never()).printBookList(books);
    }
}