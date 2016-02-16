package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

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
        // TODO
    }

    @Test
    public void testGetBookDetailsReturnsAuthor() {
        // TODO
    }

    @Test
    public void getTestGetBookDetailsReturnsYearPublished() {
        // TODO
    }

}