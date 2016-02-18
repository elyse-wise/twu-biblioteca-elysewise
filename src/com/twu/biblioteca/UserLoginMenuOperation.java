package com.twu.biblioteca;

/**
 * Created by Elyse on 17/02/2016.
 */
public class UserLoginMenuOperation extends MenuOperation {
    public UserLoginMenuOperation(String trigger, String description) {
        super(trigger, description);
    }

    @Override
    void execute(Library library, Console console) {
        console.printMessage("User Login Stub.");
    }
}
