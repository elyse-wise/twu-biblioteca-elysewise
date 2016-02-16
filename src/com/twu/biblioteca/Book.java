package com.twu.biblioteca;

/**
 * Created by Elyse on 16/02/2016.
 */
public class Book {

    private String title;
    private String author;
    private String yearPublished;

    Book(String title, String author, String yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getDetailsString() {
        return title + author + yearPublished;
    }
}
