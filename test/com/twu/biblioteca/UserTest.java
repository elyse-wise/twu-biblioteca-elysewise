package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Created by Elyse on 18/02/2016.
 */
public class UserTest {

    private User user;
    private UserDetails userDetails;


    @Before
    public void setup() {
        userDetails = mock(UserDetails.class);
        user = new User("123-4567", "Password", userDetails);
    }

    @Test
    public void testGetsDetailsStringFromUserDetailsObject() {
        user.getDetailsString();
        verify(userDetails).getDetailsString();
    }

    @Test
    public void testGetsColumnsStringFromUserDetailsObject() {
        user.getColumnString();
        verify(userDetails).getColumnString();
    }

    @Test
    public void testGetShortDetailsStringIncludesLibraryNumberOnly() {
        String details = user.getShortDetailsString();
        assertEquals(details.trim(), "123-4567");
    }

    @Test
    public void testGetShortColumnStringIncludesLibraryNumberOnly() {
        String details = user.getShortColumnString();
        assertEquals(details.trim(), "User Library Number");
    }

    @Test
    public void testMatchOnSameLibraryNumberAndPassword() {
        assertTrue(user.matchedBy("123-4567", "Password"));
    }

    @Test
    public void testNoMatchOnSameLibraryNumberAndDifferentPassword() {
        assertFalse(user.matchedBy("123-4567", "*&^*&^"));
    }

    @Test
    public void testNoMatchOnDifferentLibraryNumberAndSamePassword() {
        assertFalse(user.matchedBy("123-9967", "Password"));
    }
}