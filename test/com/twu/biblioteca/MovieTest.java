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
        movie = new Movie("MyName", "MyYear", "MyDirector", "10");
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
        assertTrue(details.contains("10"));
    }

    @Test
    public void getColumnsStringReturnsExpectedColumns() {
        String details = movie.getColumnString();
        assertTrue(details.contains("Name"));
        assertTrue(details.contains("Year"));
        assertTrue(details.contains("Director"));
        assertTrue(details.contains("Rating"));
    }

    @Test
    public void testMovieAcceptsRatingBetween1and10() {
        for (int i=1; i <= 10; i++) {
            String rating = Integer.toString(i);
            Movie movie = new Movie("a", "b", "c", rating);
            assertTrue(movie.getDetailsString().contains(rating));
        }
    }

    @Test
    public void testMovieSetsRatingAsUnratedForRatingsBelow1() {
        Movie movie = new Movie("a", "b", "c", "0");
        assertTrue(movie.getDetailsString().contains("unrated"));
    }

    @Test
    public void testMovieSetsRatingAsUnratedForRatingsAbove10() {
        Movie movie = new Movie("a", "b", "c", "11");
        assertTrue(movie.getDetailsString().contains("unrated"));
    }

    @Test
    public void testMovieSetsRatingAsUnratedForRatingsThatAreNotNumbers() {
            Movie movie = new Movie("a", "b", "c", "((%*^&%*&^%*&^%*^&*&*");
            assertTrue(movie.getDetailsString().contains("unrated"));
    }
}