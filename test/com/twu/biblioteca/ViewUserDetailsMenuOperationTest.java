package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Elyse on 19/02/2016.
 */
public class ViewUserDetailsMenuOperationTest {

    private ViewUserDetailsMenuOperation viewUserDetailsMenuOperation;
    private Library library;
    private Console console;
    private User user;

    @Before
    public void setup() {
        viewUserDetailsMenuOperation = new ViewUserDetailsMenuOperation("UD", "View User Details");
        library = mock(Library.class);
        console = mock(Console.class);
    }

    @Test
    public void testUserNeedsToBeLoggedIn() {
        assertTrue(viewUserDetailsMenuOperation.needsLogin());
    }

    @Test
    public void testViewUserDetailsIsTriggeredByLowerCaseUD() {
        assertTrue(viewUserDetailsMenuOperation.isTriggeredBy("ud"));
    }

    @Test
    public void testViewUserDetailsIsTriggeredByUpperCaseUD() {
        assertTrue(viewUserDetailsMenuOperation.isTriggeredBy("UD"));
    }

    @Test
    public void testUserDetailsPrintedOnExecute() {
        viewUserDetailsMenuOperation.execute(library, console);
        verify(console).printUserDetails(library.activeUser());
    }

}