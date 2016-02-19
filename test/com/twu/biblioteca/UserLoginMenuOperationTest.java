package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Elyse on 18/02/2016.
 */
public class UserLoginMenuOperationTest {

    UserLoginMenuOperation userLoginMenuOperation;
    private Library library;
    private Console console;
    private String validLibraryNumber = "123-4567";

    @Before
    public void setup() {
        this.userLoginMenuOperation = new UserLoginMenuOperation("UL", "User Login");
        this.library = mock(Library.class);
        this.console = mock(Console.class);
    }

    @Test
    public void testUserDoesNotNeedToBeLoggedIn() {
        assertFalse(userLoginMenuOperation.needsLogin());
    }

    @Test
    public void testUserIsPromptedForLibraryNumber() {
        userLoginMenuOperation.execute(library, console);
        verify(console).printMessage("> Enter library number: ");
    }

    @Test
    public void testUserIsPromptedForPasswordIfLibraryNumberEnteredSuccessfully() {
        String commands[] = {validLibraryNumber};
        setUserInput(commands);

        userLoginMenuOperation.execute(library, console);
        verify(console).printMessage("> Enter password: ");
    }


    @Test
    public void testUserIsNotPromptedForPasswordIfLibraryNumberNotEnteredSuccessfully() {
        String commands[] = {"*&^%&^%*^%*%*&^"};
        setUserInput(commands);

        userLoginMenuOperation.execute(library, console);
        verify(console, never()).printMessage("> Enter password: ");
    }

    @Test
    public void testAttemptedLoginWhenUserEntersAValidLibraryNumberAndValidPassword() {
        String commands[] = {validLibraryNumber, "ValidPassword"};
        setUserInput(commands);

        userLoginMenuOperation.execute(library, console);
        verify(library).attemptUserLogin(validLibraryNumber, "ValidPassword");
    }

    @Test
    public void testUserIsNotLoggedInToLibraryWhenTheyEnterAnInValidLibraryNumberAndInvalidPassword() {
        String commands[] = {"%*&*^%*^*", "*&^%*&^%**%&*"};
        setUserInput(commands);

        userLoginMenuOperation.execute(library, console);
        verify(library, never()).attemptUserLogin(validLibraryNumber, "ValidPassword");
    }

    @Test
    public void testWarnsWhenUserEntersInvalidLibraryNumber() {
        String commands[] = {"%*&*^%*^*", "*&^%*&^%**%&*"};
        setUserInput(commands);

        userLoginMenuOperation.execute(library, console);
        verify(console).printMessage("Invalid library number");
    }

    @Test
    public void testWarnsUnsuccessfulWhenUserAccountDoesNotExist() {
        String commands[] = {validLibraryNumber, "&^%*&^%**&^%*"};
        setUserInput(commands);

        userLoginMenuOperation.execute(library, console);
        verify(console).printMessage("Your attempt was unsuccessful");
    }

    @Test
    public void testDisplaysuccessMessageWhenUserLogsIn() {
        when(library.attemptUserLogin(validLibraryNumber, "ValidPassword")).thenReturn(true);
        String commands[] = {validLibraryNumber, "ValidPassword"};
        setUserInput(commands);

        userLoginMenuOperation.execute(library, console);
        verify(console).printMessage("You are now logged in");
    }

    private void setUserInput(String inputs[]) {
        List<String> inputList = Arrays.asList(inputs);
        when(console.getUserCommand()).thenAnswer(AdditionalAnswers.returnsElementsOf(inputList));
    }

}