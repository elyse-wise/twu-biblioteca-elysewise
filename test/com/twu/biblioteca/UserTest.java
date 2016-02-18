package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Elyse on 18/02/2016.
 */
public class UserTest {

    private User user;


    @Before
    public void setup() {
        user = new User("123-4567", "Password", "MyName", "MyEmail", "MyPhoneNumber");
    }

    @Test
    public void testGetUserDetailsReturnsName() {
        String details = user.getDetailsString();
        assertTrue(details.contains("MyName"));
    }

    @Test
    public void testGetUserDetailsReturnsEmailAddress() {
        String details = user.getDetailsString();
        assertTrue(details.contains("MyEmail"));
    }

    @Test
    public void testGetUserDetailsReturnsPhoneNumber() {
        String details = user.getDetailsString();
        assertTrue(details.contains("MyPhone"));
    }

    @Test
    public void testGetUserDetailsDoesNotReturnPassword() {
        String details = user.getDetailsString();
        assertFalse(details.contains("Password"));
    }

    @Test
    public void getColumnsStringReturnsExpectedColumns() {
        String details = user.getColumnString();
        assertTrue(details.contains("Name"));
        assertTrue(details.contains("Email Address"));
        assertTrue(details.contains("Phone Number"));
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