package com.twu.biblioteca;

/**
 * Created by Elyse on 16/02/2016.
 */
public class Book implements LibraryItem {

    private String title;
    private String author;
    private String yearPublished;

    Book(String title, String author, String yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getColumnString() {
        return String.format("%-30s %-30s %-30s", "Title", "Author", "Year Published");
    }

    public String getDetailsString() {
        return String.format("%-30.28s %-30.28s %-30.28s", title, author, yearPublished);
    }
}
