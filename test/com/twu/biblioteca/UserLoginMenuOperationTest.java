package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Elyse on 18/02/2016.
 */
public class UserLoginMenuOperationTest {

    UserLoginMenuOperation userMenuLoginOperation;

    @Before
    public void setup() {
        this.userMenuLoginOperation = new UserLoginMenuOperation("UL", "User Login");
    }

    @Test
    public void testUserNeedsToBeLoggedIn() {
        assertFalse(userMenuLoginOperation.needsLogin());
    }
}