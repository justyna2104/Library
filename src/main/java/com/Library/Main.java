package com.Library;


import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){

        BookService bookService = new BookService();
        ReaderService readerService = new ReaderService();
        AuthorService authorService = new AuthorService();
        GenreService genreService = new GenreService();
        CheckOutService checkOutService = new CheckOutService();

        JFrame window = new JFrame("Main Menu");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JDesktopPane desktop = new JDesktopPane();
        JPanel mainPanel = new JPanel();
        //GridLayout menuLayout = new GridLayout(0,2);
        //mainPanel.setLayout(menuLayout);

        mainPanel.add(new Label(""));
        JLabel manu = new JLabel("MAIN MENU");
        manu.setFont(new Font("Calibri", Font.BOLD, 20));
        mainPanel.add(manu);


        JLabel bookManagment = new JLabel("BOOK MANAGMENT:");
        bookManagment.setFont(new Font("Calibri", Font.BOLD, 17));
        mainPanel.add(bookManagment, BorderLayout.LINE_END);



        window.add(BorderLayout.NORTH, mainPanel);
        window.add(BorderLayout.CENTER, desktop);
        window.setSize(600, 600);
        window.setVisible(true);
    }
}