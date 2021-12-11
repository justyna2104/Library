package com.Library;


import javax.swing.*;

public class Main {
    public static void main(String[] args){

        BookService bookService = new BookService();
        ReaderService readerService = new ReaderService();
        AuthorService authorService = new AuthorService();
        GenreService genreService = new GenreService();
        CheckOutService checkOutService = new CheckOutService();

        JFrame window = new JFrame("Main Menu");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        final JDesktopPane desktop = new JDesktopPane();

        JLabel
    }
}