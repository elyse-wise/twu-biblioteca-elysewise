package com.twu.biblioteca;

import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Console console = new Console(System.in, System.out);
        Library library = buildLibrary();
        BibliotecaApp application = new BibliotecaApp(console, library, buildMenuOperations());
        application.run();
    }

    private static List<MenuOperation> buildMenuOperations() {
        List<MenuOperation> menuOperations = new ArrayList<MenuOperation>();
        menuOperations.add(new ListBooksMenuOperation("LB", "List Books"));
        menuOperations.add(new CheckoutBookMenuOperation("CB", "Checkout Book"));
        menuOperations.add(new ReturnBookMenuOperation("RB", "Return Book"));
        menuOperations.add(new CheckoutMovieMenuOperation("CM", "Checkout Movie"));
        menuOperations.add(new ListMoviesMenuOperation("LM", "List Movies"));
        menuOperations.add(new QuitMenuOperation("Q", "Quit"));
        return menuOperations;
    }

    private static Library buildLibrary() {
        Library library = new Library();
        library.setAvailableBooks(buildAvailableBooks());
        library.setCheckedOutBooks(buildCheckedOutBooks());
        library.setAvailableMovies(buildAvailableMovies());
        return library;
    }

    private static List<Book> buildAvailableBooks() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("A Tale of Two Cities", "Charles Dickens", "1859"));
        books.add(new Book("The Lord of The Rings", "J.R.R. Tolkein", "1954"));
        books.add(new Book("The Alchemist", "Paulo Coelho", "1988"));
        books.add(new Book("The Little Prince", "Antoine De-Saint Exupery", "1943"));
        books.add(new Book("The Da Vinci Code", "Dan Brown", "2003"));
        books.add(new Book("Black Beauty", "Anna Sewell", "1877"));
        return books;
    }

    private static List<Book> buildCheckedOutBooks() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("The Hobbit", "J.R.R. Tolkein", "1937"));
        books.add(new Book("The Lion, The Witch and The Wardrobe", "C.S. Lewis", "1950"));
        books.add(new Book("Charlotte's Web", "E.B. White", "1952"));
        return books;
    }

    private static List<Movie> buildAvailableMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie("MovieA", "1859", "DirectorA", ""));
        movies.add(new Movie("MovieB", "1983", "DirectorB", "2"));
        movies.add(new Movie("MovieC", "2002", "DirectorC", "3"));
        return movies;
    }
}
