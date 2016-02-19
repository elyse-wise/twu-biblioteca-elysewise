package com.twu.biblioteca;

/**
 * Created by Elyse on 19/02/2016.
 */
public class UserDetails {

    String name;
    String email;
    String phone;

    public UserDetails (String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getColumnString() {
        return String.format("%-30s %-30s %-30s", "Name", "Email Address", "Phone Number");
    }

    public String getDetailsString() {
        return String.format("%-30.28s %-30.28s %-30.28s", name, email, phone);
    }
}
