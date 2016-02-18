package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ConsoleTest {

    private InputStream in;
    private PrintStream out;
    private Console console;

    @Before
    public void setUpStreams() {
        in = mock(InputStream.class);
        out = mock(PrintStream.class);
        console = new Console(in, out);
    }

    @Test
    public void testPrintMessage() {
        console.printMessage("this is a TEST!..");
        verify(out).println("this is a TEST!..");
    }

    @Test
    public void testWelcomeMessageIsDisplayed() {
        console.printWelcome();
        verify(out).println("Welcome to Biblioteca!");
    }

    @Test
    public void testNoItemsArePrintedWhenItemListIsEmpty() {
        console.printLibraryItemList(new ArrayList<LibraryItem>());
        verify(out, never()).println();
    }

    @Test
    public void testOneItemIsPrintedWhenLibraryItemListHasOneItem() {
        List<LibraryItem> items = new ArrayList<LibraryItem>();
        LibraryItem item = mock(LibraryItem.class);
        items.add(item);

        console.printLibraryItemList(items);
        verify(item, times(1)).getDetailsString();
    }

    @Test
    public void testAllItemsPrintedWhenLibraryItemListHasMultipleItems() {
        List<LibraryItem> items = new ArrayList<LibraryItem>();
        LibraryItem test1 = mock(LibraryItem.class);
        LibraryItem test2 = mock(LibraryItem.class);
        LibraryItem test3 = mock(LibraryItem.class);
        items.add(test1);
        items.add(test2);
        items.add(test3);

        console.printLibraryItemList(items);
        verify(test1, times(1)).getDetailsString();
        verify(test2, times(1)).getDetailsString();
        verify(test3, times(1)).getDetailsString();
    }

    @Test
    public void testColumnsArePrintedWhenShowingLibraryItemList() {
        List<LibraryItem> items = new ArrayList<LibraryItem>();
        LibraryItem testItem = mock(LibraryItem.class);
        items.add(testItem);

        console.printLibraryItemList(items);
        verify(testItem, times(1)).getColumnString();
    }

    @Test
    public void testMenuDisplayHasListBooksOption() {
        List<MenuOperation> menuOperations = new ArrayList<MenuOperation>();
        MenuOperation op = mock(MenuOperation.class);
        when(op.getDescription()).thenReturn("List Books");
        menuOperations.add(op);

        console.printMenuOptions(menuOperations);
        verify(out).println("List Books");
    }

    @Test
    public void testWarnInvalidMenuOption() {
        console.warnInvalidMenuOption();
        verify(out).println("Select a valid option!");
    }

}
