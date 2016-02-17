package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Elyse on 17/02/2016.
 */
public class ListMoviesMenuOperationTest {

    private ListMoviesMenuOperation listMoviesMenuOperation;
    private Library library;
    private Console console;

    @Before
    public void setup() {
        this.listMoviesMenuOperation = new ListMoviesMenuOperation("M", "List Movies");
        this.library = mock(Library.class);
        this.console = mock(Console.class);
    }

    @Test
    public void testListMoviesIsTriggeredByLowerCaseM() {
        assertTrue(listMoviesMenuOperation.isTriggeredBy("m"));
    }

    @Test
    public void testListMoviesIsTriggeredByUpperCaseM() {
        assertTrue(listMoviesMenuOperation.isTriggeredBy("M"));
    }

    @Test
    public void testListMoviesHeaderPrintedOnExecute() {
        listMoviesMenuOperation.execute(library, console);
        verify(console).printListMoviesHeader();
    }

    @Test
    public void testMovieListPrintedOnExecute() {
        listMoviesMenuOperation.execute(library, console);
        verify(console).printMovieList(library.availableMovies());
    }
}