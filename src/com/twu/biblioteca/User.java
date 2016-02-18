package com.twu.biblioteca;

/**
 * Created by Elyse on 18/02/2016.
 */
public class User {

    String username;
    String password;
    String name;
    String email;
    String phone;

    public User(String username, String password, String name, String email, String phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.name = null;
        this.email = null;
        this.phone = null;
    }

    public String getColumnString() {
        return String.format("%-30s %-30s %-30s", "Name", "Email Address", "Phone Number");
    }

    public String getDetailsString() {
        return String.format("%-30.28s %-30.28s %-30.28s", name, email, phone);
    }

    public Boolean matchedBy(String username, String password) {
        return (this.username.equals(username) && this.password.equals(password));
    }
}
