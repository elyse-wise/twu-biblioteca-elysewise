package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Elyse on 19/02/2016.
 */
public class UserDetailsTest {

    private UserDetails userDetails;

    @Before
    public void setup() {
        userDetails = new UserDetails("MyName", "MyEmail", "MyPhoneNumber");
    }

    @Test
    public void testGetUserDetailsReturnsName() {
        String details = userDetails.getDetailsString();
        assertTrue(details.contains("MyName"));
    }

    @Test
    public void testGetUserDetailsReturnsEmailAddress() {
        String details = userDetails.getDetailsString();
        assertTrue(details.contains("MyEmail"));
    }

    @Test
    public void testGetUserDetailsReturnsPhoneNumber() {
        String details = userDetails.getDetailsString();
        assertTrue(details.contains("MyPhone"));
    }

    @Test
    public void getColumnsStringReturnsExpectedColumns() {
        String details = userDetails.getColumnString();
        assertTrue(details.contains("Name"));
        assertTrue(details.contains("Email Address"));
        assertTrue(details.contains("Phone Number"));
    }
}