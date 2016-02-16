package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by Elyse on 16/02/2016.
 */
public class BookTest {

    private Book book;


    @Before
    public void setup() {
        book = new Book("MyTitle", "MyAuthor", "MyYearPublished");
    }

    @Test
    public void testGetBookDetailsReturnsTitle() {
        String details = book.getDetailsString();
        assertTrue(details.contains("MyTitle"));
    }

    @Test
    public void testGetBookDetailsReturnsAuthor() {
        String details = book.getDetailsString();
        assertTrue(details.contains("MyAuthor"));
    }

    @Test
    public void getTestGetBookDetailsReturnsYearPublished() {
        String details = book.getDetailsString();
        assertTrue(details.contains("MyYearPublished"));
    }

    @Test
    public void getColumnsStringReturnsExpectedColumns() {
        String details = book.getColumnString();
        assertTrue(details.contains("Title"));
        assertTrue(details.contains("Author"));
        assertTrue(details.contains("Year Published"));
    }

}