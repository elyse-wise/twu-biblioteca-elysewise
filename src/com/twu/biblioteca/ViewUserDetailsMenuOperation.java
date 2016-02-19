package com.twu.biblioteca;

/**
 * Created by Elyse on 19/02/2016.
 */
public class ViewUserDetailsMenuOperation extends MenuOperation {

    public ViewUserDetailsMenuOperation(String trigger, String description) {
        super(trigger, description);
    }

    @Override
    void execute(Library library, Console console) {
        console.printUserDetails(library.activeUser());
    }

    @Override
    public Boolean needsLogin() {
        return true;
    }
}
