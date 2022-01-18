package com.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Swing {

    public void swingView(){
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

        GridLayout infoLayout = new GridLayout(0,1);

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
        mainPanel.add(new JLabel("Search book by author"));

        final JButton searchByAuthorButton = new JButton("Search");
        searchByAuthorButton.setPreferredSize(new Dimension(40,5));
        searchByAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //final JInternalFrame searchByAuthorFrame = new JInternalFrame(("Search by author"), true, true, true, true);
                JFrame searchByAuthorFrame = new JFrame();
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
                        java.util.List<Book> books = bookService.findByAuthors(inputAuthor.getText());
                        if(books.isEmpty()){
                            JOptionPane.showMessageDialog(searchByAuthorFrame, "No book with such an author has been found", "Found books",
                                    JOptionPane.PLAIN_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(searchByAuthorFrame, bookService.oneString(books), "Found books",
                                    JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                });
                searchByAuthorFrame.setSize(400, 100);
                searchByAuthorFrame.setVisible(true);
                searchByAuthorFrame.setSize(400, 100);
                //desktop.add(searchByAuthorFrame);
                //searchByAuthorFrame.moveToFront();
            }
        });
        mainPanel.add(searchByAuthorButton);

        //*********************************************************SEARCH BY TITLE*************************************
        mainPanel.add(new JLabel("Search book by title"));

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
                        java.util.List<Book> books = bookService.findByTitle(inputTitle.getText());
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
        mainPanel.add(new JLabel("Search book by genre"));

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
                        java.util.List<Book> books = bookService.findByGenre(inputGenre.getText());
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
        mainPanel.add(new JLabel("Show all books"));

        final JButton showAllButton = new JButton("Show");
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<Book> books = bookService.listEntity();
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

        //***********************************************REMOVE BOOK FROM LIBRARY'S SYSTEM************************************

        mainPanel.add(new JLabel("Remove book by title"));
        final JButton removeBook = new JButton("Remove");
        removeBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JInternalFrame searchBookToRemoveFrame = new JInternalFrame(("Search book to remove"), true, true, true, true);
                JPanel searchBookToRemovePanel = new JPanel();
                searchBookToRemovePanel.setLayout(searchLayout);
                searchBookToRemoveFrame.add(searchBookToRemovePanel, BorderLayout.NORTH);

                searchBookToRemovePanel.add(new JLabel("Enter book's title: "));
                JTextField inputTitle = new JTextField();
                searchBookToRemovePanel.add(inputTitle);
                JButton search = new JButton("Search");
                searchBookToRemovePanel.add(search);
                search.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final JInternalFrame chooseBookToRemoveFrame = new JInternalFrame(("Choose book to remove"), true, true, true, true);
                        JPanel chooseBookToRemovePanel = new JPanel();
                        chooseBookToRemovePanel.setLayout(menuLayout);
                        chooseBookToRemoveFrame.add(chooseBookToRemovePanel, BorderLayout.NORTH);

                        java.util.List<Book> books = bookService.findByTitle(inputTitle.getText());
                        if(books.isEmpty()){
                            JOptionPane.showMessageDialog(window, "No book with such a title has been found", "Found books",
                                    JOptionPane.PLAIN_MESSAGE);
                        }else {
                            for(Book b : books){
                                chooseBookToRemovePanel.add(new Label(b.toString()));
                                JButton remove = new JButton("Remove");
                                chooseBookToRemovePanel.add(remove);
                                remove.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        final JInternalFrame removeBookFrame = new JInternalFrame(("Remove book"), true, true, true, true);
                                        JPanel removeBookPanel = new JPanel();
                                        removeBookPanel.setLayout(menuLayout);
                                        removeBookFrame.add(removeBookPanel, BorderLayout.NORTH);

                                        removeBookPanel.add(new Label("Are you sure you want to remove " + b.toString() + "?"));
                                        JButton yes = new JButton(("YES"));
                                        removeBookPanel.add(yes);
                                        yes.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if(bookService.isCheckedOut(b)){
                                                    JOptionPane.showMessageDialog(window, "Book is checked out, therefore cannot be removed", "Found books",
                                                            JOptionPane.PLAIN_MESSAGE);
                                                }else {
                                                    bookService.deleteEntity(b);
                                                    JOptionPane.showMessageDialog(window, "Book has been removed", "Found books",
                                                            JOptionPane.PLAIN_MESSAGE);
                                                    removeBookFrame.setVisible(false);
                                                    desktop.remove(removeBookFrame);
                                                    chooseBookToRemoveFrame.setVisible(false);
                                                    desktop.remove(chooseBookToRemoveFrame);
                                                }
                                            }
                                        });
                                        JButton no = new JButton(("NO"));
                                        removeBookPanel.add(no);
                                        no.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                removeBookFrame.setVisible(false);
                                                desktop.remove(removeBookFrame);
                                            }
                                        });
                                        removeBookFrame.setVisible(true);
                                        removeBookFrame.setSize(400, 100);
                                        desktop.add(removeBookFrame);
                                        removeBookFrame.moveToFront();
                                    }
                                });
                            }
                            chooseBookToRemoveFrame.setVisible(true);
                            chooseBookToRemoveFrame.setSize(400, 100);
                            desktop.add(chooseBookToRemoveFrame);
                            chooseBookToRemoveFrame.moveToFront();
                        }
                    }
                });
                searchBookToRemoveFrame.setVisible(true);
                searchBookToRemoveFrame.setSize(400, 100);
                desktop.add(searchBookToRemoveFrame);
                searchBookToRemoveFrame.moveToFront();
            }
        });
        mainPanel.add(removeBook);

        //****************************************CHECK AVAILABILITY**************************************
        mainPanel.add(new JLabel("Check out book availability"));
        JButton checkAvailability = new JButton("Check");
        mainPanel.add(checkAvailability);
        checkAvailability.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JInternalFrame searchBookToCheckFrame = new JInternalFrame(("Search book to check"), true, true, true, true);
                JPanel searchBookToCheckPanel = new JPanel();
                searchBookToCheckPanel.setLayout(searchLayout);
                searchBookToCheckFrame.add(searchBookToCheckPanel, BorderLayout.NORTH);

                searchBookToCheckPanel.add(new JLabel("Enter book's title: "));
                JTextField inputTitle = new JTextField();
                searchBookToCheckPanel.add(inputTitle);
                JButton search = new JButton("Search");
                searchBookToCheckPanel.add(search);
                search.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final JInternalFrame chooseBookToCheckFrame = new JInternalFrame(("Choose book to check"), true, true, true, true);
                        JPanel chooseBookToCheckPanel = new JPanel();
                        chooseBookToCheckPanel.setLayout(menuLayout);
                        chooseBookToCheckFrame.add(chooseBookToCheckPanel, BorderLayout.NORTH);

                        java.util.List<Book> books = bookService.findByTitle(inputTitle.getText());
                        if(books.isEmpty()){
                            JOptionPane.showMessageDialog(window, "No book with such a title has been found", "Found books",
                                    JOptionPane.PLAIN_MESSAGE);
                        }else {
                            for (Book b : books){
                                chooseBookToCheckPanel.add(new Label(b.toString()));
                                JButton check = new JButton("Check");
                                chooseBookToCheckPanel.add(check);
                                check.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(bookService.checkAvailability(b)){
                                            JOptionPane.showMessageDialog(window,b.getTitle() + " is available", "Found books",
                                                    JOptionPane.PLAIN_MESSAGE);
                                        }else {
                                            JOptionPane.showMessageDialog(window,b.getTitle() + " is not available", "Found books",
                                                    JOptionPane.PLAIN_MESSAGE);
                                        }
                                    }
                                });
                            }
                            chooseBookToCheckFrame.setVisible(true);
                            chooseBookToCheckFrame.setSize(400, 100);
                            desktop.add(chooseBookToCheckFrame);
                            chooseBookToCheckFrame.moveToFront();
                        }
                    }
                });
                searchBookToCheckFrame.setVisible(true);
                searchBookToCheckFrame.setSize(400, 100);
                desktop.add(searchBookToCheckFrame);
                searchBookToCheckFrame.moveToFront();
            }
        });

        //***************************************RETURN BOOK************************************************
        mainPanel.add(new JLabel("Return book"));
        JButton returBook = new JButton("Return");
        mainPanel.add(returBook);
        returBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JInternalFrame searchReturningReaderFrame = new JInternalFrame(("Search returning reader"), true, true, true, true);
                JPanel searchReturningReaderPanel = new JPanel();
                searchReturningReaderPanel.setLayout(searchLayout);
                searchReturningReaderFrame.add(searchReturningReaderPanel, BorderLayout.NORTH);

                searchReturningReaderPanel.add(new JLabel("Enter returning reader's name: "));
                JTextField inputName = new JTextField();
                searchReturningReaderPanel.add(inputName);
                JButton search = new JButton("Search");
                searchReturningReaderPanel.add(search);
                search.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final JInternalFrame chooseReturningReaderFrame = new JInternalFrame(("Choose reader"), true, true, true, true);
                        JPanel chooseReturningReaderPanel = new JPanel();
                        chooseReturningReaderPanel.setLayout(menuLayout);
                        chooseReturningReaderFrame.add(chooseReturningReaderPanel, BorderLayout.NORTH);

                        java.util.List<Reader> readers = readerService.findByName(inputName.getText());
                        if(readers.isEmpty()){
                            JOptionPane.showMessageDialog(window," There is no such a reader", "Found readers",
                                    JOptionPane.PLAIN_MESSAGE);
                        }else{
                            for(Reader r : readers){
                                chooseReturningReaderPanel.add(new Label(r.toString()));
                                JButton choose =  new JButton("Choose");
                                chooseReturningReaderPanel.add(choose);
                                choose.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        final JInternalFrame returnBookFrame = new JInternalFrame(("Return book"), true, true, true, true);
                                        JPanel returnBookPanel = new JPanel();
                                        returnBookPanel.setLayout(menuLayout);
                                        returnBookFrame.add(returnBookPanel, BorderLayout.NORTH);

                                        java.util.List<Book> books = readerService.checkedOutBooks(r);
                                        if(books.isEmpty()){
                                            JOptionPane.showMessageDialog(window,"This reader has no checked out books", "Found readers",
                                                    JOptionPane.PLAIN_MESSAGE);
                                        }else {
                                            for(Book b : books){
                                                returnBookPanel.add(new Label("BOOK: " + b.toString()));
                                                JButton returnThisBook =  new JButton("Return");
                                                returnBookPanel.add(returnThisBook);
                                                returnThisBook.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        bookService.handBack(b,r);
                                                        JOptionPane.showMessageDialog(window,"Book has been returned", "Found readers",
                                                                JOptionPane.PLAIN_MESSAGE);
                                                        returnBookFrame.setVisible(false);
                                                        desktop.remove(returnBookFrame);
                                                    }
                                                });
                                            }
                                            returnBookFrame.setVisible(true);
                                            returnBookFrame.setSize(400, 100);
                                            desktop.add(returnBookFrame);
                                            returnBookFrame.moveToFront();
                                        }
                                    }
                                });
                            }
                            chooseReturningReaderFrame.setVisible(true);
                            chooseReturningReaderFrame.setSize(400, 100);
                            desktop.add(chooseReturningReaderFrame);
                            chooseReturningReaderFrame.moveToFront();
                        }
                    }
                });
                searchReturningReaderFrame.setVisible(true);
                searchReturningReaderFrame.setSize(400, 100);
                desktop.add(searchReturningReaderFrame);
                searchReturningReaderFrame.moveToFront();
            }
        });

        //*********************************************CHECKING OUT BOOK**********************************************
        mainPanel.add(new JLabel("Checkout book"));
        JButton checkoutBook = new JButton("Checkout");
        mainPanel.add(checkoutBook);
        checkoutBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JInternalFrame searchCheckingoutReaderFrame = new JInternalFrame(("Search checking out reader"), true, true, true, true);
                JPanel searchCheckingoutReaderPanel = new JPanel();
                searchCheckingoutReaderPanel.setLayout(searchLayout);
                searchCheckingoutReaderFrame.add(searchCheckingoutReaderPanel, BorderLayout.NORTH);

                searchCheckingoutReaderPanel.add(new JLabel("Enter reader's name: "));
                JTextField inputName = new JTextField();
                searchCheckingoutReaderPanel.add(inputName);
                JButton searchReader = new JButton("Search");
                searchCheckingoutReaderPanel.add(searchReader);
                searchReader.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final JInternalFrame chooseCheckingoutReaderFrame = new JInternalFrame(("Choose reader"), true, true, true, true);
                        JPanel chooseCheckingoutReaderPanel = new JPanel();
                        chooseCheckingoutReaderPanel.setLayout(menuLayout);
                        chooseCheckingoutReaderFrame.add(chooseCheckingoutReaderPanel, BorderLayout.NORTH);

                        java.util.List<Reader> readers = readerService.findByName(inputName.getText());
                        if(readers.isEmpty()){
                            JOptionPane.showMessageDialog(window," There is no such a reader", "Found readers",
                                    JOptionPane.PLAIN_MESSAGE);
                        }else {
                            for(Reader r : readers){
                                chooseCheckingoutReaderPanel.add(new Label(r.getFirstname() + " " + r.getLastname()));
                                JButton choose =  new JButton("Choose");
                                chooseCheckingoutReaderPanel.add(choose);
                                choose.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(!readerService.checkCheckOutAbility(r)){
                                            JOptionPane.showMessageDialog(window,"This reader has too many books", "Found readers",
                                                    JOptionPane.PLAIN_MESSAGE);
                                        }else {
                                            final JInternalFrame seachBookToCheckoutFrame = new JInternalFrame(("Search book"), true, true, true, true);
                                            JPanel searchBookToCheckoutPanel = new JPanel();
                                            searchBookToCheckoutPanel.setLayout(searchLayout);
                                            seachBookToCheckoutFrame.add(searchBookToCheckoutPanel, BorderLayout.NORTH);

                                            searchBookToCheckoutPanel.add(new JLabel("Enter book's title: "));
                                            JTextField inputTitle = new JTextField();
                                            searchBookToCheckoutPanel.add(inputTitle);
                                            JButton searchBook = new JButton("Search");
                                            searchBookToCheckoutPanel.add(searchBook);
                                            searchBook.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    final JInternalFrame chooseBookToCheckoutFrame = new JInternalFrame(("Choose book"), true, true, true, true);
                                                    JPanel chooseBookToCheckoutPanel = new JPanel();
                                                    chooseBookToCheckoutPanel.setLayout(menuLayout);
                                                    chooseBookToCheckoutFrame.add(chooseBookToCheckoutPanel, BorderLayout.NORTH);

                                                    java.util.List<Book> books = bookService.findByTitle(inputTitle.getText());
                                                    if(books.isEmpty()){
                                                        JOptionPane.showMessageDialog(window," There is no such a book", "Found books",
                                                                JOptionPane.PLAIN_MESSAGE);
                                                    }else {
                                                        for (Book b : books){
                                                            chooseBookToCheckoutPanel.add(new Label(b.getTitle()));
                                                            JButton checkout = new JButton("Checkout");
                                                            chooseBookToCheckoutPanel.add(checkout);
                                                            checkout.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if(!bookService.checkAvailability(b)){
                                                                        JOptionPane.showMessageDialog(window,"This book is not available", "Found books",
                                                                                JOptionPane.PLAIN_MESSAGE);
                                                                    }else {
                                                                        bookService.checkOut(b, r);
                                                                        JOptionPane.showMessageDialog(window,b.getTitle() + " has been checked out by " + r.getFirstname() + " " + r.getLastname(), "Found books",
                                                                                JOptionPane.PLAIN_MESSAGE);
                                                                    }
                                                                }
                                                            });
                                                        }
                                                        chooseBookToCheckoutFrame.setVisible(true);
                                                        chooseBookToCheckoutFrame.setSize(400, 100);
                                                        desktop.add(chooseBookToCheckoutFrame);
                                                        chooseBookToCheckoutFrame.moveToFront();
                                                    }
                                                }
                                            });
                                            seachBookToCheckoutFrame.setVisible(true);
                                            seachBookToCheckoutFrame.setSize(400, 100);
                                            desktop.add(seachBookToCheckoutFrame);
                                            seachBookToCheckoutFrame.moveToFront();
                                        }
                                    }
                                });
                            }
                            chooseCheckingoutReaderFrame.setVisible(true);
                            chooseCheckingoutReaderFrame.setSize(400, 100);
                            desktop.add(chooseCheckingoutReaderFrame);
                            chooseCheckingoutReaderFrame.moveToFront();
                        }
                    }
                });
                searchCheckingoutReaderFrame.setVisible(true);
                searchCheckingoutReaderFrame.setSize(400, 100);
                desktop.add(searchCheckingoutReaderFrame);
                searchCheckingoutReaderFrame.moveToFront();
            }
        });

        //**************************************ADD BOOK*********************************************
        mainPanel.add(new JLabel("Add new book"));
        JButton addBook = new JButton("Add");
        mainPanel.add(addBook);
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JInternalFrame addBookFrame = new JInternalFrame(("Add book"), true, true, true, true);
                JPanel addBookPanel = new JPanel();
                addBookPanel.setLayout(menuLayout);
                JScrollPane addBookScrollPane = new JScrollPane(addBookPanel);
                addBookScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                addBookScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                //addBookFrame.add(addBookPanel, BorderLayout.NORTH);
                addBookFrame.getContentPane().add(addBookScrollPane);


                addBookPanel.add(new JLabel("Enter book's title: "));
                JTextField inputTitle = new JTextField();
                addBookPanel.add(inputTitle);

                addBookPanel.add(new JLabel("Enter amount of available copies: "));
                JTextField inputAmount = new JTextField();
                addBookPanel.add(inputAmount);

                addBookPanel.add(new JLabel("Enter ISBN: "));
                JTextField inputISBN = new JTextField();
                addBookPanel.add(inputISBN);

                //checkBoxes for genres
                addBookPanel.add(new JLabel("Choose genres: "));
                addBookPanel.add(new Label());
                java.util.List<Genre> genres = genreService.listEntity();
                java.util.List<JCheckBox> checkBoxes = new ArrayList<>();
                for (Genre g : genres){
                    JCheckBox genre = new JCheckBox(g.getName());
                    addBookPanel.add(genre);
                    checkBoxes.add(genre);
                }

                // adding authors
                java.util.List<Author> authors = new ArrayList<>();
                addBookPanel.add(new JLabel("Add authors"));
                JButton addAuthor = new JButton("Add");
                addBookPanel.add(addAuthor);
                addAuthor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final JInternalFrame addAuthorsFrame = new JInternalFrame(("Add authors"), true, true, true, true);
                        JPanel addAuthorsPanel = new JPanel();
                        addAuthorsPanel.setLayout(menuLayout);
                        addAuthorsFrame.add(addAuthorsPanel, BorderLayout.NORTH);

                        addAuthorsPanel.add(new JLabel("Enter author's firstname: "));
                        JTextField inputFirstname = new JTextField();
                        addAuthorsPanel.add(inputFirstname);

                        addAuthorsPanel.add(new JLabel("Enter author's lasttname: "));
                        JTextField inputLastname = new JTextField();
                        addAuthorsPanel.add(inputLastname);

                        addAuthorsPanel.add(new JLabel("Enter author's nationality: "));
                        JTextField inputNationality = new JTextField();
                        addAuthorsPanel.add(inputNationality);

                        addAuthorsPanel.add(new JLabel("Enter author's date of birth (YYYY-MM-DD): "));
                        JTextField inputDOB = new JTextField();
                        addAuthorsPanel.add(inputDOB);

                        JButton addAuthor = new JButton("Add");
                        addAuthorsPanel.add(addAuthor);
                        addAuthor.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Author author = new Author(inputFirstname.getText(), inputLastname.getText(),
                                        LocalDate.parse(inputDOB.getText()), inputNationality.getText());
                                if(authorService.alreadyExists(author)){
                                    JOptionPane.showMessageDialog(window,"This author already exists and has been added as an author of this book", "Warning",
                                            JOptionPane.PLAIN_MESSAGE);
                                    Author existingAuthor = authorService.findTheSame(author);
                                    authors.add(existingAuthor);
                                }else {
                                    authorService.addEntity(author);
                                    authors.add(authorService.findTheSame(author));
                                    JOptionPane.showMessageDialog(window,"This author has been added as an author of this book", "Warning",
                                            JOptionPane.PLAIN_MESSAGE);
                                }

                                addAuthorsFrame.setVisible(false);
                                desktop.remove(addAuthorsFrame);
                            }
                        });
                        addAuthorsFrame.setVisible(true);
                        addAuthorsFrame.setSize(400, 100);
                        desktop.add(addAuthorsFrame);
                        addAuthorsFrame.moveToFront();
                    }
                });

                JButton add = new JButton("Add Book");
                addBookPanel.add(add);
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Set<Genre> genresSet = new HashSet<>();
                        for(JCheckBox cb : checkBoxes){
                            if(cb.isSelected()){
                                Genre genre = genreService.findByName(cb.getText());
                                genresSet.add(genre);
                            }
                        }

                        Set<Author> authorSet = new HashSet<>(authors);
                        Book newBook = new Book(inputTitle.getText(), Long.parseLong(inputISBN.getText()), authorSet, genresSet, Integer.parseInt(inputAmount.getText()));

                        if(bookService.checkIfExists(newBook)){
                            JOptionPane.showMessageDialog(window,"Book with that ISBN already exists.", "Warning",
                                    JOptionPane.PLAIN_MESSAGE);
                        }else{
                            bookService.addEntity(newBook);
                            Book book = bookService.findByISBN(newBook.getIsbnNumber());
                            for(Author a : authors){
                                a.getBooks().add(book);
                                authorService.updateEntity(a);
                            }
                            JOptionPane.showMessageDialog(window,"Book has been added.", "Warning",
                                    JOptionPane.PLAIN_MESSAGE);
                            authors.clear(); // ToDo nwm czy to trzeba ale obawiam sie że jak nie wyczyszcze to przy dodaniu dwóch książek przy jednym uruchomieniu to czy nie beda zostawać
                        }
                    }
                });
                addBookFrame.setVisible(true);
                addBookFrame.setSize(400, 100);
                desktop.add(addBookFrame);
                addBookFrame.moveToFront();
            }
        });

        //*************************************************EDIT BOOK************************************************
        mainPanel.add(new JLabel("Edit book"));
        JButton editBook = new JButton("Edit");
        mainPanel.add(editBook);
        editBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JInternalFrame searchBookToEditFrame = new JInternalFrame(("Search book to edit"), true, true, true, true);
                JPanel searchBookToEditPanel = new JPanel();
                searchBookToEditPanel.setLayout(searchLayout);
                searchBookToEditFrame.add(searchBookToEditPanel, BorderLayout.NORTH);

                searchBookToEditPanel.add(new JLabel("Enter title of book you want to edit: "));
                JTextField inputTitle = new JTextField();
                searchBookToEditPanel.add(inputTitle);
                JButton bookToEdit = new JButton("Search");
                searchBookToEditPanel.add(bookToEdit);
                bookToEdit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final JInternalFrame chooseBookToEditFrame = new JInternalFrame(("Choose book"), true, true, true, true);
                        JPanel chooseBookToEditPanel = new JPanel();
                        chooseBookToEditPanel.setLayout(menuLayout);
                        chooseBookToEditFrame.add(chooseBookToEditPanel, BorderLayout.NORTH);

                        java.util.List<Book> books = bookService.findByTitle(inputTitle.getText());
                        if(books.isEmpty()){
                            JOptionPane.showMessageDialog(window," There is no such a book", "Found books",
                                    JOptionPane.PLAIN_MESSAGE);
                        }else {
                            for(Book b : books){
                                chooseBookToEditPanel.add(new Label(b.getTitle()));
                                JButton chooseToEdit = new JButton("Edit");
                                chooseBookToEditPanel.add(chooseToEdit);
                                chooseToEdit.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        final JInternalFrame editBookFrame = new JInternalFrame(("Edit book"), true, true, true, true);
                                        JPanel editBookPanel = new JPanel();
                                        editBookPanel.setLayout(menuLayout);
                                        editBookFrame.add(editBookPanel, BorderLayout.NORTH);

                                        editBookPanel.add(new JLabel("Edit book's title: "));
                                        JTextField inputTitle = new JTextField(b.getTitle());
                                        editBookPanel.add(inputTitle);

                                        editBookPanel.add(new JLabel("Edit amount of available copies: "));
                                        JTextField inputAmount = new JTextField(String.valueOf(b.getAmountAvailable()));
                                        editBookPanel.add(inputAmount);

                                        editBookPanel.add(new JLabel("Edit ISBN: "));
                                        JTextField inputISBN = new JTextField(b.getIsbnNumber().toString());
                                        editBookPanel.add(inputISBN);

                                        //checkBoxes for genres
                                        editBookPanel.add(new JLabel("Edit genres: "));
                                        editBookPanel.add(new Label());
                                        java.util.List<Genre> genres = genreService.listEntity();
                                        java.util.List<JCheckBox> checkBoxes = new ArrayList<>();
                                        for (Genre g : genres){
                                            JCheckBox genre = new JCheckBox(g.getName());
                                            editBookPanel.add(genre);
                                            checkBoxes.add(genre);
                                        }

                                        java.util.List<Author> newAuthors = new ArrayList<>();
                                        editBookPanel.add(new JLabel("Edit authors"));
                                        JButton editAuthors = new JButton("Edit authors");
                                        editBookPanel.add(editAuthors);
                                        editAuthors.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                final JInternalFrame editAuthorFrame = new JInternalFrame(("Edit author"), true, true, true, true);
                                                JPanel editAuthorPanel = new JPanel();
                                                editAuthorPanel.setLayout(menuLayout);
                                                editAuthorFrame.add(editAuthorPanel, BorderLayout.NORTH);

                                                if(b.getAuthors().isEmpty()){
                                                    JOptionPane.showMessageDialog(window,"This book has no known authors.", "Warning",
                                                            JOptionPane.PLAIN_MESSAGE);
                                                }else {
                                                    for(Author a : b.getAuthors()){
                                                        editAuthorPanel.add(new JLabel(a.toString()));
                                                        JButton removeAuthor = new JButton("Remove");
                                                        editAuthorPanel.add(removeAuthor);
                                                        removeAuthor.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                a.getBooks().remove(b);
                                                                b.getAuthors().remove(a);
                                                                authorService.updateEntity(a);
                                                                JOptionPane.showMessageDialog(window,"This author has been removed from list of authors of " + b.getTitle(), "Warning",
                                                                        JOptionPane.PLAIN_MESSAGE);
                                                            }
                                                        });
                                                    }
                                                }

                                                editAuthorPanel.add(new JLabel("Add new author"));
                                                JButton addAuthor = new JButton("Add");
                                                editAuthorPanel.add(addAuthor);
                                                addAuthor.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        final JInternalFrame addAuthorsFrame = new JInternalFrame(("Add authors"), true, true, true, true);
                                                        JPanel addAuthorsPanel = new JPanel();
                                                        addAuthorsPanel.setLayout(menuLayout);
                                                        addAuthorsFrame.add(addAuthorsPanel, BorderLayout.NORTH);

                                                        addAuthorsPanel.add(new JLabel("Enter author's firstname: "));
                                                        JTextField inputFirstname = new JTextField();
                                                        addAuthorsPanel.add(inputFirstname);

                                                        addAuthorsPanel.add(new JLabel("Enter author's lasttname: "));
                                                        JTextField inputLastname = new JTextField();
                                                        addAuthorsPanel.add(inputLastname);

                                                        addAuthorsPanel.add(new JLabel("Enter author's nationality: "));
                                                        JTextField inputNationality = new JTextField();
                                                        addAuthorsPanel.add(inputNationality);

                                                        addAuthorsPanel.add(new JLabel("Enter author's date of birth (YYYY-MM-DD): "));
                                                        JTextField inputDOB = new JTextField();
                                                        addAuthorsPanel.add(inputDOB);

                                                        JButton addAuthor = new JButton("Add");
                                                        addAuthorsPanel.add(addAuthor);
                                                        addAuthor.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                Author author = new Author(inputFirstname.getText(), inputLastname.getText(),
                                                                        LocalDate.parse(inputDOB.getText()), inputNationality.getText());
                                                                if(authorService.alreadyExists(author)){
                                                                    JOptionPane.showMessageDialog(window,"This author already exists and has been added as an author of this book", "Warning",
                                                                            JOptionPane.PLAIN_MESSAGE);
                                                                    Author existingAuthor = authorService.findTheSame(author);
                                                                    newAuthors.add(existingAuthor);
                                                                }else {
                                                                    authorService.addEntity(author);
                                                                    newAuthors.add(authorService.findTheSame(author));
                                                                    JOptionPane.showMessageDialog(window,"This author has been added as an author of this book", "Warning",
                                                                            JOptionPane.PLAIN_MESSAGE);
                                                                }

                                                                addAuthorsFrame.setVisible(false);
                                                                desktop.remove(addAuthorsFrame);
                                                            }
                                                        });
                                                        addAuthorsFrame.setVisible(true);
                                                        addAuthorsFrame.setSize(400, 100);
                                                        desktop.add(addAuthorsFrame);
                                                        addAuthorsFrame.moveToFront();
                                                    }
                                                });
                                                editAuthorFrame.setVisible(true);
                                                editAuthorFrame.setSize(400, 100);
                                                desktop.add(editAuthorFrame);
                                                editAuthorFrame.moveToFront();
                                            }
                                        });

                                        JButton edit = new JButton("Edit");
                                        editBookPanel.add(edit);
                                        edit.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                b.setTitle(inputTitle.getText());
                                                b.setAmountAvailable(Integer.parseInt(inputAmount.getText()));
                                                b.setIsbnNumber(Long.parseLong(inputISBN.getText()));

                                                for(Author a : newAuthors){
                                                    b.getAuthors().add(a);
                                                    a.getBooks().add(b);
                                                    authorService.updateEntity(a);
                                                }

                                                Set<Genre> genresSet = new HashSet<>();
                                                for(JCheckBox cb : checkBoxes){
                                                    if(cb.isSelected()){
                                                        Genre genre = genreService.findByName(cb.getText());
                                                        genresSet.add(genre);
                                                    }
                                                }
                                                if(!genresSet.isEmpty()){
                                                    b.setGenres(genresSet);
                                                }

                                                editBookFrame.setVisible(false);
                                                desktop.remove(editBookFrame);
                                                bookService.updateEntity(b);
                                                JOptionPane.showMessageDialog(window,"Book has been edited", "Warning",
                                                        JOptionPane.PLAIN_MESSAGE);
                                            }

                                        });
                                        JScrollPane sp = new JScrollPane(editBookPanel);
                                        editBookFrame.add(sp);
                                        editBookFrame.setVisible(true);
                                        editBookFrame.setSize(400, 100);
                                        desktop.add(editBookFrame);
                                        editBookFrame.moveToFront();
                                    }
                                });
                            }
                            chooseBookToEditFrame.setVisible(true);
                            chooseBookToEditFrame.setSize(400, 100);
                            desktop.add(chooseBookToEditFrame);
                            chooseBookToEditFrame.moveToFront();
                        }
                    }
                });
                searchBookToEditFrame.setVisible(true);
                searchBookToEditFrame.setSize(400, 100);
                desktop.add(searchBookToEditFrame);
                searchBookToEditFrame.moveToFront();
            }
        });

        JLabel readerManagment = new JLabel("RREADER'S CARD MANAGMENT:");
        readerManagment.setFont(new Font("Calibri", Font.BOLD, 17));
        mainPanel.add(readerManagment);
        mainPanel.add(new Label(""));

        //**************************************************ADD READER'S CARD*******************************************
        mainPanel.add(new JLabel("Add reader's card"));
        JButton addReader = new JButton("Add");
        mainPanel.add(addReader);
        addReader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JInternalFrame addReadersCardFrame = new JInternalFrame(("Add authors"), true, true, true, true);
                JPanel addReadersCardPanel = new JPanel();
                addReadersCardPanel.setLayout(menuLayout);
                addReadersCardFrame.add(addReadersCardPanel, BorderLayout.NORTH);

                addReadersCardPanel.add(new JLabel("Enter reader's firstname: "));
                JTextField inputFirstname = new JTextField();
                addReadersCardPanel.add(inputFirstname);

                addReadersCardPanel.add(new JLabel("Enter reader's lasttname: "));
                JTextField inputLastname = new JTextField();
                addReadersCardPanel.add(inputLastname);

                addReadersCardPanel.add(new JLabel("Enter reader's pesel: "));
                JTextField inputPesel = new JTextField();
                addReadersCardPanel.add(inputPesel);

                addReadersCardPanel.add(new JLabel("Enter reader's phone number: "));
                JTextField inputPhone = new JTextField();
                addReadersCardPanel.add(inputPhone);

                addReadersCardPanel.add(new JLabel("Enter reader's date of birth (YYYY-MM-DD): "));
                JTextField inputDOB = new JTextField();
                addReadersCardPanel.add(inputDOB);

                addReadersCardPanel.add(new JLabel("Enter reader's address (street, building number, local number): "));
                JTextField inputAddress = new JTextField();
                addReadersCardPanel.add(inputAddress);

                JButton add = new JButton("Add");
                addReadersCardPanel.add(add);
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Reader reader = new Reader(inputFirstname.getText().toUpperCase(), inputLastname.getText().toUpperCase(), Long.parseLong(inputPesel.getText()), Long.parseLong(inputPhone.getText()),
                                inputAddress.getText().toUpperCase(), LocalDate.parse(inputDOB.getText()));
                        if(readerService.alreadyExists(reader)){
                            JOptionPane.showMessageDialog(window,"This reader already has a card.", "Warning",
                                    JOptionPane.PLAIN_MESSAGE);
                        }else {
                            readerService.addEntity(reader);
                            JOptionPane.showMessageDialog(window,"Reader's card has been created.", "Warning",
                                    JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                });
                addReadersCardFrame.setVisible(true);
                addReadersCardFrame.setSize(400, 100);
                desktop.add(addReadersCardFrame);
                addReadersCardFrame.moveToFront();
            }
        });


        //********************************EDIT READER'S CARD***********************************************

        mainPanel.add(new JLabel("Edit reader's card"));
        JButton editReader = new JButton("Edit");
        mainPanel.add(editReader);
        editReader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JInternalFrame searchReaderToEditFrame = new JInternalFrame(("Search reader's card to edit"), true, true, true, true);
                JPanel searchReaderToEditPanel = new JPanel();
                searchReaderToEditPanel.setLayout(searchLayout);
                searchReaderToEditFrame.add(searchReaderToEditPanel, BorderLayout.NORTH);

                searchReaderToEditPanel.add(new JLabel("Enter name of reader whose card you want to edit: "));
                JTextField inputName = new JTextField();
                searchReaderToEditPanel.add(inputName);
                JButton readerToEdit = new JButton("Search");
                searchReaderToEditPanel.add(readerToEdit);
                readerToEdit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final JInternalFrame chooseReaderToEditFrame = new JInternalFrame(("Choose reader's card to edit"), true, true, true, true);
                        JPanel chooseReaderToEditPanel = new JPanel();
                        chooseReaderToEditPanel.setLayout(menuLayout);
                        chooseReaderToEditFrame.add(chooseReaderToEditPanel, BorderLayout.NORTH);

                        java.util.List<Reader> readers = readerService.findByName(inputName.getText());
                        if(readers.isEmpty()){
                            JOptionPane.showMessageDialog(window,"No reader has been found.", "Warning",
                                    JOptionPane.PLAIN_MESSAGE);
                        }else {
                            for(Reader r : readers){
                                chooseReaderToEditPanel.add(new JLabel(r.toString()));
                                JButton choose = new JButton("Edit");
                                chooseReaderToEditPanel.add(choose);
                                choose.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        final JInternalFrame editReaderFrame = new JInternalFrame(("Edit reader's card"), true, true, true, true);
                                        JPanel editReaderPanel = new JPanel();
                                        editReaderPanel.setLayout(menuLayout);
                                        editReaderFrame.add(editReaderPanel, BorderLayout.NORTH);

                                        editReaderPanel.add(new JLabel("Edit reader's firstname"));
                                        JTextField inputFirstname = new JTextField(r.getFirstname());
                                        editReaderPanel.add(inputFirstname);

                                        editReaderPanel.add(new JLabel("Edit reader's lastname"));
                                        JTextField inputLastname = new JTextField(r.getLastname());
                                        editReaderPanel.add(inputLastname);

                                        editReaderPanel.add(new JLabel("Edit reader's pesel"));
                                        JTextField inputPesel = new JTextField(String.valueOf(r.getPeselNumber()));
                                        editReaderPanel.add(inputPesel);

                                        editReaderPanel.add(new JLabel("Edit reader's phone number"));
                                        JTextField inputPhone = new JTextField(String.valueOf(r.getPhoneNumber()));
                                        editReaderPanel.add(inputPhone);

                                        editReaderPanel.add(new JLabel("Edit reader's date of birth (YYYY-MM-DD)"));
                                        JTextField inputDOB = new JTextField(String.valueOf(r.getBirthday()));
                                        editReaderPanel.add(inputDOB);

                                        editReaderPanel.add(new JLabel("Edit reader's address (street, building number, local number)"));
                                        JTextField inputAddress = new JTextField(r.getAddress());
                                        editReaderPanel.add(inputAddress);

                                        JButton edit = new JButton("Edit");
                                        editReaderPanel.add(edit);
                                        edit.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                r.setFirstname(inputFirstname.getText());
                                                r.setLastname(inputLastname.getText());
                                                r.setAddress(inputAddress.getText());
                                                r.setBirthday(LocalDate.parse(inputDOB.getText()));
                                                r.setPeselNumber(Long.parseLong(inputPesel.getText()));
                                                r.setPhoneNumber(Long.parseLong(inputPhone.getText()));
                                                readerService.updateEntity(r);
                                                JOptionPane.showMessageDialog(window,"Reader's card has been edited.", "Warning",
                                                        JOptionPane.PLAIN_MESSAGE);
                                            }
                                        });

                                        editReaderFrame.setVisible(true);
                                        editReaderFrame.setSize(400, 100);
                                        desktop.add(editReaderFrame);
                                        editReaderFrame.moveToFront();
                                    }
                                });
                            }
                            chooseReaderToEditFrame.setVisible(true);
                            chooseReaderToEditFrame.setSize(400, 100);
                            desktop.add(chooseReaderToEditFrame);
                            chooseReaderToEditFrame.moveToFront();
                        }
                    }
                });
                searchReaderToEditFrame.setVisible(true);
                searchReaderToEditFrame.setSize(400, 100);
                desktop.add(searchReaderToEditFrame);
                searchReaderToEditFrame.moveToFront();
            }
        });

        //*******************************************REMOVE READER'S CARD**********************************************

        mainPanel.add(new JLabel("Remove reader's card."));
        JButton removeReader = new JButton("Remove");
        mainPanel.add(removeReader);
        removeReader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JInternalFrame searchReaderToRemoveFrame = new JInternalFrame(("Search reader's card to remove"), true, true, true, true);
                JPanel searchReaderToRemovePanel = new JPanel();
                searchReaderToRemovePanel.setLayout(searchLayout);
                searchReaderToRemoveFrame.add(searchReaderToRemovePanel, BorderLayout.NORTH);

                searchReaderToRemovePanel.add(new JLabel("Enter name of reader whose card you want to remove: "));
                JTextField inputName = new JTextField();
                searchReaderToRemovePanel.add(inputName);
                JButton readerToRemove = new JButton("Search");
                searchReaderToRemovePanel.add(readerToRemove);
                readerToRemove.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final JInternalFrame chooseReaderToRemoveFrame = new JInternalFrame(("Choose reader's card to remove"), true, true, true, true);
                        JPanel chooseReaderToRemovePanel = new JPanel();
                        chooseReaderToRemovePanel.setLayout(menuLayout);
                        chooseReaderToRemoveFrame.add(chooseReaderToRemovePanel, BorderLayout.NORTH);

                        java.util.List<Reader> readers = readerService.findByName(inputName.getText());
                        if(readers.isEmpty()){
                            JOptionPane.showMessageDialog(window,"No reader has been found.", "Warning",
                                    JOptionPane.PLAIN_MESSAGE);
                        }else {
                            for(Reader r : readers){
                                chooseReaderToRemovePanel.add(new JLabel(r.toString()));
                                JButton remove = new JButton("Remove");
                                chooseReaderToRemovePanel.add(remove);
                                remove.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        final JInternalFrame removeReaderFrame = new JInternalFrame(("Remove reader's card"), true, true, true, true);
                                        JPanel removeReaderPanel = new JPanel();
                                        removeReaderPanel.setLayout(searchLayout);
                                        removeReaderFrame.add(removeReaderPanel, BorderLayout.NORTH);

                                        if(!readerService.checkedOutBooks(r).isEmpty()){
                                            JOptionPane.showMessageDialog(window,"This reader has checked out books, therefore their card cannot be removed.", "Warning",
                                                    JOptionPane.PLAIN_MESSAGE);
                                        }else {
                                            removeReaderPanel.add(new JLabel("Are you sure you want to remove this readers's card?"));
                                            JButton yes = new JButton("YES");
                                            removeReaderPanel.add(yes);
                                            yes.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    readerService.deleteEntity(r);
                                                    JOptionPane.showMessageDialog(window,"Reader's card has been removed.", "Warning",
                                                            JOptionPane.PLAIN_MESSAGE);
                                                    removeReaderFrame.setVisible(false);
                                                    desktop.remove(removeReaderFrame);
                                                    chooseReaderToRemoveFrame.setVisible(false);
                                                    desktop.remove(chooseReaderToRemoveFrame);
                                                }
                                            });
                                            JButton no = new JButton("NO");
                                            removeReaderPanel.add(no);
                                            no.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    removeReaderFrame.setVisible(false);
                                                    desktop.remove(removeReaderFrame);
                                                }
                                            });
                                            removeReaderFrame.setVisible(true);
                                            removeReaderFrame.setSize(400, 100);
                                            desktop.add(removeReaderFrame);
                                            removeReaderFrame.moveToFront();
                                        }
                                    }
                                });
                            }
                            chooseReaderToRemoveFrame.setVisible(true);
                            chooseReaderToRemoveFrame.setSize(400, 100);
                            desktop.add(chooseReaderToRemoveFrame);
                            chooseReaderToRemoveFrame.moveToFront();
                        }
                    }
                });
                searchReaderToRemoveFrame.setVisible(true);
                searchReaderToRemoveFrame.setSize(400, 100);
                desktop.add(searchReaderToRemoveFrame);
                searchReaderToRemoveFrame.moveToFront();
            }
        });

        //******************************CHECK IF READER IS ABLE TO CHECK OUT BOOK***************************************

        mainPanel.add(new JLabel("Check reader's ability to check out"));
        JButton checkReader = new JButton("Check");
        mainPanel.add(checkReader);
        checkReader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JInternalFrame searchReaderToCheckFrame = new JInternalFrame(("Search reader's card to check"), true, true, true, true);
                JPanel searchReaderToCheckPanel = new JPanel();
                searchReaderToCheckPanel.setLayout(searchLayout);
                searchReaderToCheckFrame.add(searchReaderToCheckPanel, BorderLayout.NORTH);

                searchReaderToCheckPanel.add(new JLabel("Enter name of reader whose card you want to check: "));
                JTextField inputName = new JTextField();
                searchReaderToCheckPanel.add(inputName);
                JButton readerToCheck = new JButton("Search");
                searchReaderToCheckPanel.add(readerToCheck);
                readerToCheck.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final JInternalFrame chooseReaderToCheckFrame = new JInternalFrame(("Choose reader's card to check"), true, true, true, true);
                        JPanel chooseReaderToCheckPanel = new JPanel();
                        chooseReaderToCheckPanel.setLayout(menuLayout);
                        chooseReaderToCheckFrame.add(chooseReaderToCheckPanel, BorderLayout.NORTH);

                        java.util.List<Reader> readers = readerService.findByName(inputName.getText());
                        if(readers.isEmpty()){
                            JOptionPane.showMessageDialog(window,"No reader has been found.", "Warning",
                                    JOptionPane.PLAIN_MESSAGE);
                        }else {
                            for(Reader r : readers){
                                chooseReaderToCheckPanel.add(new JLabel(r.toString()));
                                JButton check = new JButton("Check");
                                chooseReaderToCheckPanel.add(check);
                                check.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(readerService.checkedOutBooks(r).size() >= 4){
                                            JOptionPane.showMessageDialog(window,"Reader alredy has 4 books. Can't check ou more.", "Warning",
                                                    JOptionPane.PLAIN_MESSAGE);
                                        }
                                        else if(readerService.isOverdue(r)){
                                            JOptionPane.showMessageDialog(window,"Reader has an overdue book. Can't check out another one.", "Warning",
                                                    JOptionPane.PLAIN_MESSAGE);
                                        }else{
                                            JOptionPane.showMessageDialog(window,"This reader is able to check out "
                                                            + (4-readerService.checkedOutBooks(r).size()) + " more books.", "Warning",
                                                    JOptionPane.PLAIN_MESSAGE);
                                        }
                                    }
                                });
                            }
                            chooseReaderToCheckFrame.setVisible(true);
                            chooseReaderToCheckFrame.setSize(400, 100);
                            desktop.add(chooseReaderToCheckFrame);
                            chooseReaderToCheckFrame.moveToFront();
                        }
                    }
                });
                searchReaderToCheckFrame.setVisible(true);
                searchReaderToCheckFrame.setSize(400, 100);
                desktop.add(searchReaderToCheckFrame);
                searchReaderToCheckFrame.moveToFront();
            }
        });

        //*************************************SEARCH READER BY NAME********************************************

        mainPanel.add(new JLabel("Search reader by name"));
        JButton searchReader = new JButton("Search");
        mainPanel.add(searchReader);
        searchReader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JInternalFrame searchReaderFrame = new JInternalFrame(("Search reader's card"), true, true, true, true);
                JPanel searchReaderPanel = new JPanel();
                searchReaderPanel.setLayout(searchLayout);
                searchReaderFrame.add(searchReaderPanel, BorderLayout.NORTH);

                searchReaderPanel.add(new JLabel("Enter name of reader whose card you want to find: "));
                JTextField inputName = new JTextField();
                searchReaderPanel.add(inputName);
                JButton readerToFind = new JButton("Search");
                searchReaderPanel.add(readerToFind);
                readerToFind.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        java.util.List<Reader> readers = readerService.findByName(inputName.getText());
                        if(readers.isEmpty()){
                            JOptionPane.showMessageDialog(window,"No reader has been found.", "Warning",
                                    JOptionPane.PLAIN_MESSAGE);
                        }else {
                            JOptionPane.showMessageDialog(window,readerService.oneString(readers), "Warning",
                                    JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                });
                searchReaderFrame.setVisible(true);
                searchReaderFrame.setSize(400, 100);
                desktop.add(searchReaderFrame);
                searchReaderFrame.moveToFront();
            }
        });

        //************************************CHECK IF SOMEONE IS OVERDUE********************************************

        mainPanel.add(new JLabel("Check if someone is overdue"));
        JButton checkIfOvedue = new JButton("Check");
        mainPanel.add(checkIfOvedue);
        checkIfOvedue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JInternalFrame checkIfOverdueFrame = new JInternalFrame(("Check if someone is overdue"), true, true, true, true);
                JPanel checkIfOverduePanel = new JPanel();
                checkIfOverduePanel.setLayout(infoLayout);
                checkIfOverdueFrame.add(checkIfOverduePanel, BorderLayout.NORTH);

                java.util.List<Reader> readers = checkOutService.whoIsOverDue();
                if(readers.isEmpty()){
                    JOptionPane.showMessageDialog(window,"Nobody is overdue.", "Warning",
                            JOptionPane.PLAIN_MESSAGE);
                }else {
                    for (Reader r : readers){
                        checkIfOverduePanel.add(new JLabel(r.toString()));
                        checkIfOverduePanel.add(new JLabel("Overdue books: " + bookService.titlesinOneString(readerService.overdueBooks(r))));
                        checkIfOverduePanel.add(new JLabel());
                    }
                    checkIfOverdueFrame.setVisible(true);
                    checkIfOverdueFrame.setSize(400, 100);
                    desktop.add(checkIfOverdueFrame);
                    checkIfOverdueFrame.moveToFront();
                }
            }
        });

        //*********************************SHOW READER'S BOOKS*******************************************

        mainPanel.add(new JLabel("Check reader's books"));
        JButton checkBooks = new JButton("Check");
        mainPanel.add(checkBooks);
        checkBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JInternalFrame searchReaderBooksFrame = new JInternalFrame(("Check reader's books"), true, true, true, true);
                JPanel searchReaderBooksPanel = new JPanel();
                searchReaderBooksPanel.setLayout(searchLayout);
                searchReaderBooksFrame.add(searchReaderBooksPanel, BorderLayout.NORTH);

                searchReaderBooksPanel.add(new JLabel("Enter name of reader whose books you want to see: "));
                JTextField inputName = new JTextField();
                searchReaderBooksPanel.add(inputName);
                JButton readerToFind = new JButton("Search");
                searchReaderBooksPanel.add(readerToFind);
                readerToFind.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final JInternalFrame chooseReaderToCheckFrame = new JInternalFrame(("Choose reader's card to check"), true, true, true, true);
                        JPanel chooseReaderToCheckPanel = new JPanel();
                        chooseReaderToCheckPanel.setLayout(menuLayout);
                        chooseReaderToCheckFrame.add(chooseReaderToCheckPanel, BorderLayout.NORTH);

                        List<Reader> readers = readerService.findByName(inputName.getText());
                        if(readers.isEmpty()){
                            JOptionPane.showMessageDialog(window,"No reader has been found.", "Warning",
                                    JOptionPane.PLAIN_MESSAGE);
                        }else {
                            for(Reader r : readers){
                                chooseReaderToCheckPanel.add(new JLabel(r.toString()));
                                JButton check = new JButton("Check");
                                chooseReaderToCheckPanel.add(check);
                                check.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(readerService.checkedOutBooks(r).isEmpty()){
                                            JOptionPane.showMessageDialog(window,"This raeder has no checked out books", "Warning",
                                                    JOptionPane.PLAIN_MESSAGE);
                                        }else {
                                            JOptionPane.showMessageDialog(window,bookService.titlesinOneString(readerService.checkedOutBooks(r)), "Warning",
                                                    JOptionPane.PLAIN_MESSAGE);
                                        }
                                    }
                                });
                            }
                            chooseReaderToCheckFrame.setVisible(true);
                            chooseReaderToCheckFrame.setSize(400, 100);
                            desktop.add(chooseReaderToCheckFrame);
                            chooseReaderToCheckFrame.moveToFront();
                        }
                    }
                });
                searchReaderBooksFrame.setVisible(true);
                searchReaderBooksFrame.setSize(400, 100);
                desktop.add(searchReaderBooksFrame);
                searchReaderBooksFrame.moveToFront();
            }
        });

        window.add(BorderLayout.NORTH, mainPanel);
        //window.add(BorderLayout.CENTER, desktop);
        window.setSize(600, 1500);
        window.setVisible(true);
    }
}
