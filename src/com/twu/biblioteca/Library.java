package com.twu.biblioteca;

import java.util.*;

/**
 * Created by Elyse on 17/02/2016.
 */
public class Library {
    LinkedHashMap<Book, User> checkedOutBooks;
    private List<Book> availableBooks;
    private List<Movie> availableMovies;
    private List<User> userAccounts;
    private User activeUser;

    public Library() {
        this.availableBooks = new ArrayList<Book>();
        this.checkedOutBooks = new LinkedHashMap<Book, User>();
        this.availableMovies = new ArrayList<Movie>();
        this.userAccounts = new ArrayList<User>();
    }

    public User getUserWithCredentials(String libraryNumber, String password) {
        for (User u : userAccounts) {
            if (u.matchedBy(libraryNumber, password)) {
                return u;
            }
        }
        return null;
    }

    public Boolean validUserLogin(String libraryNumber, String password) {
        return (getUserWithCredentials(libraryNumber, password) != null);
    }

    public void attemptUserLogin(String libraryNumber, String password) {
        User u = getUserWithCredentials(libraryNumber, password);
        if (u != null) {
            setActiveUser(u);
        }
    }

    private void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public void logoutUser() {
        activeUser = null;
    }


    public User activeUser() {
        return activeUser;
    }

    public Boolean userLoggedIn() {
        return (activeUser != null);
    }


    public Boolean administratorLoggedIn() {
        return (userLoggedIn() && activeUser.hasAdministratorAccess());
    }

    public void setAvailableBooks(List<Book> availableBooks) {
        this.availableBooks = availableBooks;
    }

    public void setCheckedOutBooks(LinkedHashMap<Book, User> checkedOutBooks) {
        this.checkedOutBooks = checkedOutBooks;
    }

    public void setAvailableMovies(List<Movie> availableMovies) {
        this.availableMovies = availableMovies;
    }

    public void setUserAccounts(List<User> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public Boolean bookIsAvailable(int bookIndex) {
        return (bookIndex >= 0 && bookIndex < availableBooks.size());
    }

    public Boolean movieIsAvailable(int movieIndex) {
        return (movieIndex >= 0 && movieIndex < availableMovies.size());
    }

    public Boolean bookIsCheckedOut(int bookIndex) {
        return (bookIndex >= 0 && bookIndex < checkedOutBooks.size());
    }

    public List<Book> availableBooks() {
        return availableBooks;
    }

    public List<Book> checkedOutBooks() {
        List<Book> bookList = new ArrayList<Book>();
        bookList.addAll(checkedOutBooks.keySet());
        return bookList;
    }

    public List<Movie> availableMovies() {
        return availableMovies;
    }

    public int numberOfBooksInLibrary() {
        return availableBooks.size();
    }

    public int numberOfBooksCheckedOut() {
        return checkedOutBooks.size();
    }

    public int numberOfMoviesInLibrary() {
        return availableMovies.size();
    }

    public void checkOutBook(int bookIndex) {
        if (bookIsAvailable(bookIndex)) {
            Book b = availableBooks.remove(bookIndex);
            checkedOutBooks.put(b, activeUser);
        }
    }

    public void returnBook(int bookIndex) {
        if (bookIsCheckedOut(bookIndex)) {
            Book b = checkedOutBooks().get(bookIndex);
            checkedOutBooks.remove(b);
            availableBooks.add(b);
        }
    }

    public void checkOutMovie(int movieIndex) {
        if (movieIsAvailable(movieIndex)) {
            Movie m = availableMovies.remove(movieIndex);
        }
    }
}
