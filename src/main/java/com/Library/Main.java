package com.Library;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

        GridLayout menuLayout = new GridLayout(0,2);
        mainPanel.setLayout(menuLayout);

        GridLayout searchLayout = new GridLayout(0,3);

/*        JLabel main = new JLabel("MAIN ");
        main.setFont(new Font("Calibri", Font.BOLD, 20));
        mainPanel.add(main);

        JLabel menu = new JLabel("MENU ");
        menu.setFont(new Font("Calibri", Font.BOLD, 20));
        mainPanel.add(menu, BorderLayout.LINE_START);*/

        JLabel bookManagment = new JLabel("BOOK MANAGMENT:");
        bookManagment.setFont(new Font("Calibri", Font.BOLD, 17));
        mainPanel.add(bookManagment, BorderLayout.WEST);
        mainPanel.add(new Label(""));

        //*********************************************************SEARCH BY AUTHOR*************************************
        mainPanel.add(new Label("Search book by author"));

        final JButton searchByAuthorButton = new JButton("Search");
        searchByAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JInternalFrame searchByAuthorFrame = new JInternalFrame(("Search by author"), true, true, true, true);
                JPanel searchByAuthorPanel = new JPanel();
                searchByAuthorPanel.setLayout(searchLayout);
                searchByAuthorFrame.add(searchByAuthorPanel, BorderLayout.NORTH);

                searchByAuthorPanel.add(new JLabel("Enter author's name: "));
                JTextField inputAuthor = new JTextField();
                searchByAuthorPanel.add(inputAuthor);
                JButton search = new JButton("Search");
                searchByAuthorPanel.add(search);

                search.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        List<Book> books = bookService.findByAuthors(inputAuthor.getText());
                        if(books.isEmpty()){
                            JOptionPane.showMessageDialog(searchByAuthorFrame, "No book with such an author has been found", "Found books",
                                    JOptionPane.PLAIN_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(searchByAuthorFrame, bookService.oneString(books), "Found books",
                                    JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                });

                searchByAuthorFrame.setVisible(true);
                searchByAuthorFrame.setSize(400, 100);
                desktop.add(searchByAuthorFrame);
                searchByAuthorFrame.moveToFront();
            }
        });
        mainPanel.add(searchByAuthorButton);

        //*********************************************************SEARCH BY TITLE*************************************
        mainPanel.add(new Label("Search book by title"));

        final JButton searchByTitleButton = new JButton("Search");
        searchByTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JInternalFrame searchByTitleFrame = new JInternalFrame(("Search by title"), true, true, true, true);
                JPanel searchByTitlePanel = new JPanel();
                searchByTitlePanel.setLayout(searchLayout);
                searchByTitleFrame.add(searchByTitlePanel, BorderLayout.NORTH);

                searchByTitlePanel.add(new JLabel("Enter book's title: "));
                JTextField inputTitle = new JTextField();
                searchByTitlePanel.add(inputTitle);
                JButton search = new JButton("Search");
                searchByTitlePanel.add(search);

                search.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        List<Book> books = bookService.findByTitle(inputTitle.getText());
                        if(books.isEmpty()){
                            JOptionPane.showMessageDialog(searchByTitleFrame, "No book with such a title has been found", "Found books",
                                    JOptionPane.PLAIN_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(searchByTitleFrame, bookService.oneString(books), "Found books",
                                    JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                });

                searchByTitleFrame.setVisible(true);
                searchByTitleFrame.setSize(400, 100);
                desktop.add(searchByTitleFrame);
                searchByTitleFrame.moveToFront();
            }
        });
        mainPanel.add(searchByTitleButton);

        //*********************************************************SEARCH BY GENRE*************************************
        mainPanel.add(new Label("Search book by genre"));

        final JButton searchByGenreButton = new JButton("Search");
        searchByGenreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JInternalFrame searchByGenreFrame = new JInternalFrame(("Search by genre"), true, true, true, true);
                JPanel searchByGenrePanel = new JPanel();
                searchByGenrePanel.setLayout(searchLayout);
                searchByGenreFrame.add(searchByGenrePanel, BorderLayout.NORTH);

                searchByGenrePanel.add(new JLabel("Enter book's genre: "));
                JTextField inputGenre = new JTextField();
                searchByGenrePanel.add(inputGenre);
                JButton search = new JButton("Search");
                searchByGenrePanel.add(search);

                search.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        List<Book> books = bookService.findByGenre(inputGenre.getText());
                        if(books.isEmpty()){
                            JOptionPane.showMessageDialog(searchByGenreFrame, "No book with such a genre has been found", "Found books",
                                    JOptionPane.PLAIN_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(searchByGenreFrame, bookService.oneString(books), "Found books",
                                    JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                });

                searchByGenreFrame.setVisible(true);
                searchByGenreFrame.setSize(400, 100);
                desktop.add(searchByGenreFrame);
                searchByGenreFrame.moveToFront();
            }
        });
        mainPanel.add(searchByGenreButton);

        //*********************************************************SHOW ALL*************************************
        mainPanel.add(new Label("Show all books"));

        final JButton showAllButton = new JButton("Show");
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Book> books = bookService.listEntity();
                if(books.isEmpty()){
                    JOptionPane.showMessageDialog(window, "No book with such a genre has been found", "Found books",
                            JOptionPane.PLAIN_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(window, bookService.oneString(books), "Found books",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        mainPanel.add(showAllButton);





        window.add(BorderLayout.NORTH, mainPanel);
        window.add(BorderLayout.CENTER, desktop);
        window.setSize(600, 600);
        window.setVisible(true);
    }
}