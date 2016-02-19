package com.twu.biblioteca;

/**
 * Created by Elyse on 18/02/2016.
 */
public class User {

    String libraryNumber;
    String password;
    UserDetails userDetails;

    public User(String libraryNumber, String password) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.userDetails = null;
    }

    public User(String libraryNumber, String password, UserDetails userDetails) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.userDetails = userDetails;
    }

    public Boolean matchedBy(String libraryNumber, String password) {
        return (this.libraryNumber.equals(libraryNumber) && this.password.equals(password));
    }


    public String getColumnString() {
        if (userDetails != null)
            return userDetails.getColumnString();
        return "";
    }

    public String getDetailsString() {
        if (userDetails != null)
            return userDetails.getDetailsString();
        return "";
    }
}
