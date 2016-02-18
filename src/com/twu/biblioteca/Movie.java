package com.twu.biblioteca;

/**
 * Created by Elyse on 17/02/2016.
 */
public class Movie implements LibraryItem {

    private String name;
    private String year;
    private String director;
    private String rating;

    public Movie(String name, String year, String director, String rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = parseMovieRating(rating);
    }

    //movies have a rating 1-10 or unrated
    private String parseMovieRating(String string) {
        try {
            Integer intRating = Integer.parseInt(string);
            if (intRating > 0 && intRating <= 10) {
                return string;
            }
        } catch (NumberFormatException e) {
        }
        return "unrated";
    }

    public String getColumnString() {
        return String.format("%-30s %-30s %-30s %-30s", "Name", "Year", "Director", "Rating");
    }

    public String getDetailsString() {
        return String.format("%-30.28s %-30.28s %-30.28s %-30.28s", name, year, director, rating);
    }
}
