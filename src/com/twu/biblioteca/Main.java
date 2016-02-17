package com.twu.biblioteca;

import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Console console = new Console(System.in, System.out);
        Library library = new Library(buildAvailableBooks(), buildCheckedOutBooks());
        BibliotecaApp application = new BibliotecaApp(console, library, buildMenuOperations());
        application.run();
    }

    private static List<MenuOperation> buildMenuOperations() {
        List<MenuOperation> menuOperations = new ArrayList<MenuOperation>();
        menuOperations.add(new ListBooksMenuOperation("L", "List Books"));
        menuOperations.add(new CheckoutBookMenuOperation("C", "Checkout Book"));
        menuOperations.add(new ReturnBookMenuOperation("R", "Return Book"));
        menuOperations.add(new QuitMenuOperation("Q", "Quit"));
        return menuOperations;
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
}
