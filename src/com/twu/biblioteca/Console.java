package com.twu.biblioteca;

import java.io.*;
import java.util.List;
import java.util.Map;

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

    public void printBookList(List<Book> books) {
        if (!books.isEmpty()) {
            out.println(books.get(0).getColumnString());
        }
        for (Book b : books) {
            out.println(b.getDetailsString());
        }
    }

    public void printMenuOptions(Map<String, String> menuOptions) {
        out.println("Menu Options:");

        for (Map.Entry<String, String> menuOption : menuOptions.entrySet()) {
            out.print("\t " + menuOption.getKey() + " => ");
            out.println(menuOption.getValue());
        }
    }

    public void promptUser() {
        out.print("Enter Command: ");
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
}
