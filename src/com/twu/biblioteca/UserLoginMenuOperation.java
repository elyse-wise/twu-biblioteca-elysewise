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
        String libraryNumber = getLibraryNumberFromUser(console);
        if (isAValidLibraryNumber(libraryNumber)) {
            String password = getPasswordFromUser(console);
            if (library.attemptUserLogin(libraryNumber, password)) {
                console.printMessage("You are now logged in");
            }
            console.printMessage("Your attempt was unsuccessful");
        } else {
            console.printMessage("Invalid library number");
        }
    }

    private String getLibraryNumberFromUser(Console console) {
        console.printMessage("> Enter library number: ");
        String libraryNumber = console.getUserCommand();
        return libraryNumber;
    }

    private String getPasswordFromUser(Console console) {
        console.printMessage("> Enter password: ");
        String password = console.getUserCommand();
        return password;
    }

    private Boolean isAValidLibraryNumber(String libraryNumber) {
        return (libraryNumber != null && libraryNumber.matches("^[0-9]{3}-[0-9]{4}$"));
    }
}
