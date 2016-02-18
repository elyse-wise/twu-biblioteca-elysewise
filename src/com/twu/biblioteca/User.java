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

    public String getColumnString() {
        return null; //TODO
    }

    public String getDetailsString() {
        return null; //TODO
    }

}
