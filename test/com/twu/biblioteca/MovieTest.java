package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Elyse on 17/02/2016.
 */
public class MovieTest {
    private Movie movie;


    @Before
    public void setup() {
        movie = new Movie("MyName", "MyYear", "MyDirector", "MyRating");
    }

    @Test
    public void testGetDetailsStringIncludesName() {
        String details = movie.getDetailsString();
        assertTrue(details.contains("MyName"));
    }

    @Test
    public void testGetDetailsStringIncludesYear() {
        String details = movie.getDetailsString();
        assertTrue(details.contains("MyYear"));
    }

    @Test
    public void testGetDetailsStringIncludesDirector() {
        String details = movie.getDetailsString();
        assertTrue(details.contains("MyDirector"));
    }

    @Test
    public void testGetDetailsStringIncludesRating() {
        String details = movie.getDetailsString();
        assertTrue(details.contains("MyRating"));
    }

    @Test
    public void getColumnsStringReturnsExpectedColumns() {
        String details = movie.getColumnString();
        assertTrue(details.contains("Name"));
        assertTrue(details.contains("Year"));
        assertTrue(details.contains("Director"));
        assertTrue(details.contains("Rating"));
    }
}