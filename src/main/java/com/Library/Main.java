package com.Library;

import java.text.ParseException;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {

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
    contentPanel.addComponent((new Label("Search books by author")));
    contentPanel.addComponent(
            new Button("Search", () -> textGUI.addWindowAndWait(searchBookByAuthorWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER))
    );

















    starterWindow.setComponent(contentPanel);
    textGUI.addWindowAndWait(starterWindow);

    }
}
