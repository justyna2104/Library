package com.Library;

import java.awt.event.ComponentListener;
import java.text.ParseException;


import javax.swing.*;
import java.io.IOException;


public class Main {
    public static void main(String[] args){

        BookService bookService = new BookService();
        ReaderService readerService = new ReaderService();
        AuthorService authorService = new AuthorService();
        GenreService genreService = new GenreService();
        CheckOutService checkOutService = new CheckOutService();

    }
}