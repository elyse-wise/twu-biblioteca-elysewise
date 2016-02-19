package com.twu.biblioteca;

/**
 * Created by Elyse on 19/02/2016.
 */
public class UserLogoutMenuOperation extends MenuOperation {
    public UserLogoutMenuOperation(String trigger, String description) {
        super(trigger, description);
    }

    @Override
    void execute(Library library, Console console) {
        library.logoutUser();
        console.printMessage("You have successfully logged out.");
    }

    @Override
    public Boolean needsLogin() {
        return true;
    }
}
