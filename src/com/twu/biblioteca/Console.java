package com.twu.biblioteca;

import java.io.*;
import java.util.List;

/**
 * Created by Elyse on 16/02/2016.
 */
public class Console {

    private InputStream in;
    private PrintStream out;

    public Console(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public void printWelcome() {
        out.println("Welcome to Biblioteca!");
    }

    public void printGap() {
        out.println();
    }

    public void printMenuOptions(List<MenuOperation> menuOperations) {
        out.println("Menu Options:");

        for (MenuOperation op : menuOperations) {
            printMenuOption(op);
        }
    }

    public void printMenuOption(MenuOperation op) {
        out.print("\t " + op.getTrigger() + " => ");
        out.println(op.getDescription());
    }

    public void promptUserForMenuOption() {
        out.print("> Enter Menu Option: ");
    }

    public String getUserCommand() {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String command = null;
        try {
            command = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return command;
    }


    public void printLibraryItemList(List<? extends LibraryItem> items) {
        for (int i = 0; i < items.size(); i++) {
            if (i == 0) printLibraryItemColumns(items.get(0));
            printLibraryItemDetails(i, items.get(i));
        }
    }

    public void printLibraryItemColumns(LibraryItem libraryItem) {
        out.print(String.format("\t%-4s", ""));
        out.println(libraryItem.getColumnString());
    }

    public void printLibraryItemDetails(int i, LibraryItem libraryItem) {
        out.print(String.format("\t%-4s", i + "."));
        out.println(libraryItem.getDetailsString());
    }

    public void promptUserForItemSelection() {
        out.print("> Enter Item Number: ");
    }
    public void warnInvalidMenuOption() {
        out.println("Select a valid option!");
    }

    public void printListItemsHeader() {
        out.println("Items Currently In Library:");
    }

    public void printCheckoutItemsHeader() {
        out.println("Items Available For Checkout:");
    }

    public void thankUserForCheckingOutBook() {
        out.println("Thank you! Enjoy the book");
    }

    public void warnInvalidBookSelectionForCheckout() {
        out.println("That book is not available");
    }

    public void printReturnBooksHeader() {
        out.println("Items Available For Return:");
    }

    public void thankUserForReturningBook() {
        out.println("Thank you for returning the book");
    }

    public void warnInvalidBookSelectionForReturn() {
        out.println("That is not a valid book to return");
    }

    public void printExitMessage() {
        out.println("Exiting Biblioteca...");
    }
}
