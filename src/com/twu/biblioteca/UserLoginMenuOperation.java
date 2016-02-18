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
        console.printMessage("> Enter library number: ");
        if(isAValidLibraryNumber(console.getUserCommand())) {
            console.printMessage("> Enter password: ");
            if (isAValidPassword(console.getUserCommand())) {
                library.setUserLoggedIn(true);
            }
        }
    }

    private Boolean isAValidLibraryNumber(String libraryNumber) {
        return (libraryNumber != null && libraryNumber.matches("^[0-9]{3}-[0-9]{4}$"));
    }

    private Boolean isAValidPassword(String password) {
        return (password != null && password.equals("ValidPassword"));
    }
}
