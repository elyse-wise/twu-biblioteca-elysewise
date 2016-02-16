package com.twu.biblioteca;

import java.io.*;
import java.util.List;

/**
 * Created by Elyse on 16/02/2016.
 */
public class Console {

    private PrintStream out;

    public Console(PrintStream out) {
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

    public void printMenuOptions() {
        out.println("Menu Options:");
        out.print("\tl: ");
        out.println("List Books");
    }

    public String getUserCommand(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        System.out.print("Enter Command: ");
        String command = null;
        try {
            command = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return command;
    }
}
