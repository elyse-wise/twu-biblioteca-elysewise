package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Elyse on 18/02/2016.
 */
public class UserLogoutMenuOperationTest {

    UserLogoutMenuOperation userLogoutMenuOperation;
    Library library;
    Console console;

    @Before
    public void setup() {
        this.userLogoutMenuOperation = new UserLogoutMenuOperation("LO", "User Logout");
        this.library = mock(Library.class);
        this.console = mock(Console.class);
    }

    @Test
    public void testUserNeedsToBeLoggedIn() {
        assertTrue(userLogoutMenuOperation.needsLogin());
    }

    @Test
    public void testUserIsLoggedOutOnExecution() {
        userLogoutMenuOperation.execute(library, console);
        verify(library).logoutUser();
    }

    @Test
    public void testSuccessfulLogoutMessageIsPrinted() {
        userLogoutMenuOperation.execute(library, console);
        verify(console).printMessage("You have successfully logged out.");
    }

}