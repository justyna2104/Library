package com.Library;

import java.awt.event.ComponentListener;
import java.text.ParseException;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {

        BookService bookService = new BookService();

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = terminalFactory.createScreen();
        screen.startScreen();

        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
        final Window starterWindow = new BasicWindow("Starter Window");

        Panel contentPanel = new Panel(new GridLayout(4));

        GridLayout gridLayout = (GridLayout)contentPanel.getLayoutManager();
        gridLayout.setHorizontalSpacing(10);
        gridLayout.setTopMarginSize(1);

        Label title = new Label("MAIN MANU");
        title.setLayoutData(GridLayout.createLayoutData(
               GridLayout.Alignment.CENTER,
               GridLayout.Alignment.CENTER,
               true,
               false,
               15,
                10
        ));
        contentPanel.addComponent(title);

        final Window searchBookByAuthorWindow = new BasicWindow("Search Window");
        contentPanel.addComponent(new Label("Search books by author"));
        contentPanel.addComponent(
                new Button("Search", () -> textGUI.addWindowAndWait(searchBookByAuthorWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER))
        );

        Panel searchByAuthorPanel = new Panel(new GridLayout(4));


        searchByAuthorPanel.addComponent(new Label("Enter authors name: "));
        TextBox inputName = new TextBox();
        searchByAuthorPanel.addComponent(inputName);
        searchByAuthorPanel.addComponent(new Button("Search", () -> {
            String authorName = inputName.getText();
            List<Book> books = bookService.findByAuthors(authorName);

            MessageDialog.showMessageDialog(textGUI, "Found Books", bookService.oneString(books), MessageDialogButton.OK);
        }));












        searchBookByAuthorWindow.setComponent(searchByAuthorPanel);
        starterWindow.setComponent(contentPanel);
        textGUI.addWindowAndWait(starterWindow);

    }
}
