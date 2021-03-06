package com.twu.biblioteca;

/**
 * Created by Elyse on 18/02/2016.
 */
public class User {

    private String libraryNumber;
    private String password;
    private UserDetails userDetails;
    private Boolean isAdministrator;

    public User(String libraryNumber, String password) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.userDetails = null;
        this.isAdministrator = false;
    }

    public User(String libraryNumber, String password, UserDetails userDetails) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.userDetails = userDetails;
        this.isAdministrator = false;
    }

    public void giveAdministratorAccess() {
        isAdministrator = true;
    }

    public Boolean hasAdministratorAccess() {
        return isAdministrator;
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

    public String getShortColumnString() {
        return String.format("%-30s ", "User Library Number");
    }

    public String getShortDetailsString() {
        return String.format("%-30.28s ", libraryNumber);
    }
}
