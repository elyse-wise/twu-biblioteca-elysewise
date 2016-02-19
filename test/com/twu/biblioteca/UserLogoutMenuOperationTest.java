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

    @Before
    public void setup() {
        this.userLogoutMenuOperation = new UserLogoutMenuOperation("LO", "User Logout");
    }

    @Test
    public void testUserNeedsToBeLoggedIn() {
        assertTrue(userLogoutMenuOperation.needsLogin());
    }

    @Test
    public void testUserIsLoggedOutOnExecution() {
        Library library = mock(Library.class);
        userLogoutMenuOperation.execute(library, mock(Console.class));
        verify(library).logoutUser();
    }

}