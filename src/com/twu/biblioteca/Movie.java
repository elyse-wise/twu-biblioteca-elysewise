package com.twu.biblioteca;

/**
 * Created by Elyse on 17/02/2016.
 */
public class Movie {

    private String name;
    private String year;
    private String director;
    private String rating;

    public Movie(String name, String year, String director, String rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public String getDetailsString() {
        return String.format("%-30.28s %-30.28s %-30.28s %-30.28s", name, year, director, rating);
    }
}
