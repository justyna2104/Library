package com.Library;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Lanterna {

    public void lanternaView() throws IOException {

        BookService bookService = new BookService();
        ReaderService readerService = new ReaderService();
        AuthorService authorService = new AuthorService();
        GenreService genreService = new GenreService();
        CheckOutService checkOutService = new CheckOutService();

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = terminalFactory.createScreen();
        screen.startScreen();

        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
        final Window starterWindow = new BasicWindow("Starter Window");

        Panel contentPanel = new Panel(new GridLayout(2)); // main panel

        GridLayout gridLayout = (GridLayout)contentPanel.getLayoutManager();
        gridLayout.setHorizontalSpacing(10);
        gridLayout.setTopMarginSize(1);

        Label menuTitle = new Label("MAIN MANU");
        menuTitle.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.CENTER,
                GridLayout.Alignment.CENTER,
                true,
                false,
                15,
                10
        ));
        contentPanel.addComponent(menuTitle);

        contentPanel.addComponent(new Label("BOOK MANAGEMENT:"));
        contentPanel.addComponent(new Label(""));

        // SEARCHING BY AUTHOR
        final Window searchBookByAuthorWindow = new BasicWindow("Search Window"); // window to search book by author
        contentPanel.addComponent(new Label("Search books by author"));
        contentPanel.addComponent(
                new Button("Search", () -> textGUI.addWindowAndWait(searchBookByAuthorWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER))
        );

        Panel searchByAuthorPanel = new Panel(new GridLayout(3)); // panel for searching by author
        searchByAuthorPanel.addComponent(new Label("Enter authors name: "));
        TextBox inputName = new TextBox();

        searchByAuthorPanel.addComponent(inputName);
        searchByAuthorPanel.addComponent(new Button("Search", () -> {
            String authorName = inputName.getText();
            List<Book> books = bookService.findByAuthors(authorName);
            if(books.isEmpty()){
                MessageDialog.showMessageDialog(textGUI, "Found Books", "No book has been found.", MessageDialogButton.OK);
            }else {
                MessageDialog.showMessageDialog(textGUI, "Found Books", bookService.oneString(books), MessageDialogButton.OK);
            }
        }));
        searchByAuthorPanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(searchBookByAuthorWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.END)));


        // SEARCHING BY TITLE
        final Window searchBookByTitleWindow = new BasicWindow("Search Window"); // window to search book by menuTitle
        contentPanel.addComponent(new Label("Search books by title"));
        contentPanel.addComponent(
                new Button("Search", () -> textGUI.addWindowAndWait(searchBookByTitleWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER))
        );

        Panel searchByTitlePanel = new Panel(new GridLayout(3)); // panel for searching by title
        searchByTitlePanel.addComponent(new Label("Enter title: "));
        TextBox inputTitle = new TextBox();
        searchByTitlePanel.addComponent(inputTitle);
        searchByTitlePanel.addComponent(new Button("Search", () -> {
            String title = inputTitle.getText();
            List<Book> books = bookService.findByTitle(title);
            if(books.isEmpty()){
                MessageDialog.showMessageDialog(textGUI, "Found Books", "No book has been found.", MessageDialogButton.OK);
            }else {
                MessageDialog.showMessageDialog(textGUI, "Found Books", bookService.oneString(books), MessageDialogButton.OK);
            }
        }));
        searchByTitlePanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(searchBookByTitleWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.END)));

        //SEARCH BY GENRE
        final Window searchBookByGenreWindow = new BasicWindow("Search Window"); // window to search book by genre
        contentPanel.addComponent(new Label("Search books by genre"));
        contentPanel.addComponent(
                new Button("Search", () -> textGUI.addWindowAndWait(searchBookByGenreWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER))
        );

        Panel searchByGenrePanel = new Panel(new GridLayout(3)); // panel for searching by menuTitle
        searchByGenrePanel.addComponent(new Label("Enter genre: "));
        TextBox inputGenre = new TextBox();
        searchByGenrePanel.addComponent(inputGenre);
        searchByGenrePanel.addComponent(new Button("Search", () -> {
            String genre = inputGenre.getText();
            List<Book> books = bookService.findByGenre(genre);
            if(books.isEmpty()){
                MessageDialog.showMessageDialog(textGUI, "Found Books", "No book has been found.", MessageDialogButton.OK);
            }else{
                MessageDialog.showMessageDialog(textGUI, "Found Books", bookService.oneString(books), MessageDialogButton.OK);
            }
        }));
        searchByGenrePanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(searchBookByGenreWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.END)));

        //LIST ALL BOOKS
        contentPanel.addComponent(new Label("Show all books "));
        contentPanel.addComponent(
                new Button("Show", () -> {
                    List<Book> allBooks = bookService.listEntity();
                    if(allBooks.isEmpty()){
                        MessageDialog.showMessageDialog(textGUI, "Found Books", "There's no books in the system.", MessageDialogButton.OK);
                    }else{
                        MessageDialog.showMessageDialog(textGUI, "All Books", bookService.oneString(allBooks), MessageDialogButton.OK);
                    }
                }));


        //REMOVE BOOK FROM LIBRARY'S SYSTEM
        final Window removeBookByTitleWindow = new BasicWindow("Remove Window"); // window to remove book
        final Window chooseBookToRemoveWindow = new BasicWindow("Remove Window"); // window to remove book
        final Window areYouSureToRemoveBookWindow = new BasicWindow("Remove Window"); // window to remove book
        contentPanel.addComponent(new Label("Remove book by title "));
        contentPanel.addComponent(
                new Button("Remove", () -> textGUI.addWindowAndWait(removeBookByTitleWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER))
        );
        Panel removeBookByTitlePanel = new Panel(new GridLayout(3)); // panel for searching title to remove
        Panel chooseBookToRemovePanel = new Panel(new GridLayout(2)); // panel for choosing book
        Panel areYouSureToRemoveBookPanel = new Panel(new GridLayout(3)); // panel for asking user if they are sure

        removeBookByTitlePanel.addComponent(new Label("Enter title: "));
        TextBox inputTitleToRemove = new TextBox();
        removeBookByTitlePanel.addComponent(inputTitleToRemove);
        removeBookByTitlePanel.addComponent(new Button("Search", () -> {
            String title = inputTitleToRemove.getText();
            List<Book> books = bookService.findByTitle(title);
            if(books.isEmpty()){
                MessageDialog.showMessageDialog(textGUI, "Warning", "No book has been found.", MessageDialogButton.OK);
            }else{
                for (Book b : books) {
                    chooseBookToRemovePanel.addComponent(new Label(b.toString()));
                    chooseBookToRemovePanel.addComponent(new Button("Remove", () -> {
                        areYouSureToRemoveBookPanel.addComponent(new Label("Are you sure you want to remove " + b.getTitle() + " from library's system?"));
                        areYouSureToRemoveBookPanel.addComponent(new Button("YES", () -> {
                            bookService.deleteEntity(b);
                            MessageDialog.showMessageDialog(textGUI, "Removed", b.getTitle() + " has been removed.", MessageDialogButton.OK);
                            textGUI.removeWindow(areYouSureToRemoveBookWindow);
                            areYouSureToRemoveBookPanel.removeAllComponents();

                        }));
                        areYouSureToRemoveBookPanel.addComponent(new Button("NO", () -> {
                            textGUI.removeWindow(areYouSureToRemoveBookWindow);
                            areYouSureToRemoveBookPanel.removeAllComponents();
                        }));
                        textGUI.addWindowAndWait(areYouSureToRemoveBookWindow);
                    }));
                }
                chooseBookToRemovePanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(chooseBookToRemoveWindow)));
                textGUI.addWindowAndWait(chooseBookToRemoveWindow);
                chooseBookToRemovePanel.removeAllComponents();
            }
        }));
        removeBookByTitlePanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(removeBookByTitleWindow)));

        //CHECK AVAILABILITY
        final Window checkAvailabilityWindow = new BasicWindow("Check Availability Window"); // window to check availability
        final Window whichToCheckWindow = new BasicWindow("Check Availability Window");
        contentPanel.addComponent(new Label("Check if book is available"));
        contentPanel.addComponent(
                new Button("Check", () -> textGUI.addWindowAndWait(checkAvailabilityWindow))
                        .setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER))
        );

        Panel checkAvailabilityPanel = new Panel(new GridLayout(3)); // panel to check availability
        Panel whichToCheckPanel = new Panel(new GridLayout(2));

        checkAvailabilityPanel.addComponent(new Label("Enter title: "));
        TextBox inputTitleToCheck = new TextBox();
        checkAvailabilityPanel.addComponent(inputTitleToCheck);
        checkAvailabilityPanel.addComponent(new Button("Search", () -> {
            List<Book> books = bookService.findByTitle(inputTitleToCheck.getText());
            if(books.isEmpty()){
                MessageDialog.showMessageDialog(textGUI, "Found Books", "No book has been found.", MessageDialogButton.OK);
            }else{
                for (Book b : books) {
                    whichToCheckPanel.addComponent(new Label(b.getTitle()));
                    whichToCheckPanel.addComponent(new Button("check", () ->{
                        if(bookService.checkAvailability(b)){
                            MessageDialog.showMessageDialog(textGUI, "Checked", b.getTitle() + " is available.", MessageDialogButton.OK);
                        }else {
                            MessageDialog.showMessageDialog(textGUI, "Checked", b.getTitle() + " is not available.", MessageDialogButton.OK);
                        }
                    }));
                }
                whichToCheckPanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(whichToCheckWindow)));
                textGUI.addWindowAndWait(whichToCheckWindow);
                whichToCheckPanel.removeAllComponents();
            }
        }));
        checkAvailabilityPanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(checkAvailabilityWindow)));


        //RETURN BOOK
        final Window findReturningReaderWindow = new BasicWindow("Return Book Window"); // window to return book by reader
        final Window chooseReturningReaderWindow = new BasicWindow("Return Book Window");
        final Window returnBookWindow = new BasicWindow("Return Book Window");
        contentPanel.addComponent(new Label("Return book"));
        contentPanel.addComponent(new Button("Return", () -> textGUI.addWindowAndWait(findReturningReaderWindow))
                .setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER))
        );

        Panel findReturningReaderPanel = new Panel(new GridLayout(3));
        Panel chooseReturningReaderPanel = new Panel(new GridLayout(2));
        Panel returnBookPanel = new Panel(new GridLayout(2));

        findReturningReaderPanel.addComponent(new Label("Enter returning reader's name: "));
        TextBox inputNameOfReturningReader = new TextBox();
        findReturningReaderPanel.addComponent(inputNameOfReturningReader);
        findReturningReaderPanel.addComponent(new Button("Search", () -> {
            List<Reader> readers = readerService.findByName(inputNameOfReturningReader.getText());
            if(readers.isEmpty()){
                MessageDialog.showMessageDialog(textGUI, "Found Readers", "No reader has been found.", MessageDialogButton.OK);
            }else{
                for (Reader r : readers) {
                    chooseReturningReaderPanel.addComponent(new Label(r.getFirstname() + " " + r.getLastname()));
                    chooseReturningReaderPanel.addComponent(new Button("Choose", () -> {
                        List<Book> books = readerService.checkedOutBooks(r);
                        if(books.isEmpty()){
                            MessageDialog.showMessageDialog(textGUI, "Found Books", "This reader has no books.", MessageDialogButton.OK);
                        }else {
                            for(Book b : books){
                                returnBookPanel.addComponent(new Label(b.getTitle()));

                                returnBookPanel.addComponent(new Button("return", () -> {
                                    bookService.handBack(b, r);
                                    MessageDialog.showMessageDialog(textGUI, "Returned book", b.getTitle() + " has been returned.", MessageDialogButton.OK);
                                    textGUI.removeWindow(returnBookWindow);
                                    returnBookPanel.removeAllComponents();
                                }));
                            }
                            returnBookPanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(returnBookWindow)));
                            textGUI.addWindowAndWait(returnBookWindow);
                            returnBookPanel.removeAllComponents();
                        }
                    }));
                }
                chooseReturningReaderPanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(chooseReturningReaderWindow)));
                textGUI.addWindowAndWait(chooseReturningReaderWindow);
                chooseReturningReaderPanel.removeAllComponents();
            }
        }));
        findReturningReaderPanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(findReturningReaderWindow)));

        //CHECKING OUT BOOK
        final Window findReaderCheckingOutWindow = new BasicWindow("Check Out Window");
        final Window chooseReaderCheckingOutWindow = new BasicWindow("Check Out Window");
        final Window findBookToCheckOutWindow = new BasicWindow("Check Out Window");
        final Window chooseBookToCheckOutWindow = new BasicWindow("Check Out Window");

        contentPanel.addComponent(new Label("Check out book"));
        contentPanel.addComponent(
                new Button("Check Out", () -> textGUI.addWindowAndWait(findReaderCheckingOutWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER))
        );

        Panel findReaderCheckingOutPanel = new Panel(new GridLayout(3));
        Panel chooseReaderCheckingOutPanel = new Panel(new GridLayout(2));
        Panel findBookToCheckOutPanel = new Panel(new GridLayout(3));
        Panel chooseBookToCheckOutPanel = new Panel(new GridLayout(2));

        findReaderCheckingOutPanel.addComponent(new Label("Enter name of reader who is checking out: "));
        TextBox inputNameOfCheckingOutReader = new TextBox();
        findReaderCheckingOutPanel.addComponent(inputNameOfCheckingOutReader);
        findReaderCheckingOutPanel.addComponent(new Button("Search", () -> {
            List<Reader> readers = readerService.findByName(inputNameOfCheckingOutReader.getText());
            if(readers.isEmpty()){
                MessageDialog.showMessageDialog(textGUI, "Found Readers", "No reader has been found.", MessageDialogButton.OK);
            }else {
                for (Reader r : readers) {
                    chooseReaderCheckingOutPanel.addComponent(new Label(r.getFirstname() + " " + r.getLastname()));
                    chooseReaderCheckingOutPanel.addComponent(new Button("Choose", () ->{
                        if(readerService.checkCheckOutAbility(r)){
                            findBookToCheckOutPanel.addComponent(new Label("Enter title: "));
                            TextBox inputTitleToCheckOut = new TextBox();
                            findBookToCheckOutPanel.addComponent(inputTitleToCheckOut);
                            findBookToCheckOutPanel.addComponent(new Button("Search", () -> {
                                List<Book> books = bookService.findByTitle(inputTitleToCheckOut.getText());
                                if(books.isEmpty()){
                                    MessageDialog.showMessageDialog(textGUI, "Found Books", "No book has been found.", MessageDialogButton.OK);
                                }else {
                                    for (Book b : books) {
                                        chooseBookToCheckOutPanel.addComponent(new Label(b.getTitle()));
                                        chooseBookToCheckOutPanel.addComponent(new Button("Check Out", () -> {
                                            if(bookService.checkAvailability(b)){
                                                bookService.checkOut(b,r);
                                                MessageDialog.showMessageDialog(textGUI, "Checked Out book", b.getTitle()
                                                        + " has been checked out by " + r.getFirstname() + " " + r.getLastname(), MessageDialogButton.OK);
                                            }else{
                                                MessageDialog.showMessageDialog(textGUI, "Checked Out book", b.getTitle()
                                                        + " isn't available.", MessageDialogButton.OK);
                                            }
                                        }));
                                    }
                                    chooseBookToCheckOutPanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(chooseBookToCheckOutWindow)));
                                    textGUI.addWindowAndWait(chooseBookToCheckOutWindow);
                                    chooseBookToCheckOutPanel.removeAllComponents();
                                }
                            }));
                            findBookToCheckOutPanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(findBookToCheckOutWindow)));
                            textGUI.addWindowAndWait(findBookToCheckOutWindow);
                            findBookToCheckOutPanel.removeAllComponents();
                        }else{
                            MessageDialog.showMessageDialog(textGUI, "Checked Out book", "This reader has too many books already.", MessageDialogButton.OK);
                        }
                    }));
                }
                chooseReaderCheckingOutPanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(chooseReaderCheckingOutWindow)));
                textGUI.addWindowAndWait(chooseReaderCheckingOutWindow);
                chooseReaderCheckingOutPanel.removeAllComponents();
            }
        }));
        findReaderCheckingOutPanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(findReaderCheckingOutWindow)));

        //ADD BOOK
        final Window addBookWindow = new BasicWindow("Add Book");
        final Window addAuthorWindow = new BasicWindow("Check Out Window");

        Panel addBookPanel = new Panel(new GridLayout(2));
        Panel addAuthorPanel = new Panel(new GridLayout(2));

        contentPanel.addComponent(new Label("Add Book"));
        contentPanel.addComponent(new Button("Add", () -> textGUI.addWindowAndWait(addBookWindow))
                .setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER)));

        //Most important book info
        addBookPanel.addComponent(new Label("Enter title: "));
        TextBox inputTitleOfNewBook = new TextBox();
        addBookPanel.addComponent(inputTitleOfNewBook.withBorder(Borders.singleLine()));

        addBookPanel.addComponent(new Label("Enter amount of available copies: "));
        TextBox inputAmountOfAvailableCopies = new TextBox();
        addBookPanel.addComponent(inputAmountOfAvailableCopies.withBorder(Borders.singleLine()));

        addBookPanel.addComponent(new Label("Enter ISBN: "));
        TextBox inputISBN = new TextBox();
        addBookPanel.addComponent(inputISBN.withBorder(Borders.singleLine()));

        //Adding genres from checkBox
        addBookPanel.addComponent(new Label("Choose genres: "));
        List<Genre> genres = genreService.listEntity();
        CheckBoxList<Genre> checkBoxList = new CheckBoxList<Genre>();
        for (Genre g : genres){
            checkBoxList.addItem(g);
        }
        addBookPanel.addComponent(checkBoxList);

        //Add authors window
        List<Author> choseAuthors = new ArrayList<>();
        addBookPanel.addComponent(new Label("Add authors"));
        addBookPanel.addComponent(new Button("Add", () -> {
            addAuthorPanel.addComponent(new Label("Enter author's name: "));
            TextBox inputAuthorsName = new TextBox();
            addAuthorPanel.addComponent(inputAuthorsName.withBorder(Borders.singleLine()));

            addAuthorPanel.addComponent(new Label("Enter author's last name: "));
            TextBox inputAuthorsLastName = new TextBox();
            addAuthorPanel.addComponent(inputAuthorsLastName.withBorder(Borders.singleLine()));

            addAuthorPanel.addComponent(new Label("Enter author's nationality: "));
            TextBox inputAuthorsNationality = new TextBox();
            addAuthorPanel.addComponent(inputAuthorsNationality.withBorder(Borders.singleLine()));

            addAuthorPanel.addComponent(new Label("Enter author's date of birth (yyyy-mm-dd): "));
            TextBox inputAuthorsDateOfBirth = new TextBox();
            addAuthorPanel.addComponent(inputAuthorsDateOfBirth.withBorder(Borders.singleLine()));

            addAuthorPanel.addComponent(new Button("Add", () -> {
                Author author = new Author(inputAuthorsName.getText(), inputAuthorsLastName.getText(), LocalDate.parse(inputAuthorsDateOfBirth.getText()), inputAuthorsNationality.getText());
                if(authorService.alreadyExists(author)){
                    MessageDialog.showMessageDialog(textGUI,"Warning", "This author exists in the system and has been added as an author of the book.", MessageDialogButton.OK);
                    Author existingAuthor = authorService.findTheSame(author);
                    choseAuthors.add(existingAuthor);
                }else{
                    authorService.addEntity(author);
                    MessageDialog.showMessageDialog(textGUI,"Added author", "Author has been added to the system and as an author of the book.", MessageDialogButton.OK);
                    choseAuthors.add(authorService.findTheSame(author));
                }
            }));
            addAuthorPanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(addAuthorWindow)));
            textGUI.addWindowAndWait(addAuthorWindow);
            addAuthorPanel.removeAllComponents();
        }));

        addBookPanel.addComponent(new Button("Add Book", () -> {
            List<Genre> genresTemp = checkBoxList.getCheckedItems();
            Set<Genre> genresSet = new HashSet<>(genresTemp);
            Set<Author> authorSet = new HashSet<>(choseAuthors);

            Book newBook = new Book(inputTitleOfNewBook.getText(), Long.parseLong(inputISBN.getText()), authorSet, genresSet, Integer.parseInt(inputAmountOfAvailableCopies.getText()));

            if(bookService.checkIfExists(newBook)){
                MessageDialog.showMessageDialog(textGUI,"Warning", "Book with this ISBN already exists.", MessageDialogButton.OK);
            }else{
                bookService.addEntity(newBook);
                Book book = bookService.findByISBN(newBook.getIsbnNumber());
                for(Author a : choseAuthors){
                    a.getBooks().add(book);
                    authorService.updateEntity(a);
                }
                MessageDialog.showMessageDialog(textGUI,"Added book", "Book has been added.", MessageDialogButton.OK);
                choseAuthors.clear(); // ToDo nwm czy to trzeba ale obawiam sie że jak nie wyczyszcze to przy dodaniu dwóch książek przy jednym uruchomieniu to czy nie beda zostawać
            }
        }));
        addBookPanel.addComponent(new Label(""));
        addBookPanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(addBookWindow)));

        // EDIT BOOK
        final Window findBookToEditWindow = new BasicWindow("Edit Book");
        final Window chooseBookToEditWindow = new BasicWindow("Book to edit");
        final Window editBookWindow = new BasicWindow("Edit Book");
        final Window authorsToEditedBookWindow = new BasicWindow("New author");

        Panel findBookToEditPanel = new Panel(new GridLayout(3));
        Panel chooseBookToEditPanel = new Panel(new GridLayout(2));
        Panel editBookPanel = new Panel(new GridLayout(2));
        Panel authorsToEditedBookPanel = new Panel(new GridLayout(2));

        contentPanel.addComponent(new Label("Edit Book"));
        contentPanel.addComponent(
                new Button("Edit", () -> textGUI.addWindowAndWait(findBookToEditWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER)));

        List<Author> newAuthors = new ArrayList<>();
        findBookToEditPanel.addComponent(new Label("Enter title of book you want to edit"));
        TextBox inputTitleToEdit = new TextBox();
        findBookToEditPanel.addComponent(inputTitleToEdit);
        findBookToEditPanel.addComponent(new Button("Search", () -> {
            List<Book> books = bookService.findByTitle(inputTitleToEdit.getText());
            for(Book b : books){
                chooseBookToEditPanel.addComponent(new Label(b.toString()));
                chooseBookToEditPanel.addComponent(new Button("Edit", () -> {
                    //edit book info
                    editBookPanel.addComponent(new Label("Edit title: "));
                    TextBox inputNewTitle= new TextBox();
                    inputNewTitle.setText(b.getTitle());
                    editBookPanel.addComponent(inputNewTitle.withBorder(Borders.singleLine()));

                    editBookPanel.addComponent(new Label("Edit amount of available copies: "));
                    TextBox inputNewAmount = new TextBox();
                    inputNewAmount.setText(String.valueOf(b.getAmountAvailable()));
                    editBookPanel.addComponent(inputNewAmount.withBorder(Borders.singleLine()));

                    editBookPanel.addComponent(new Label("Edit ISBN: "));
                    TextBox inputNewISBN = new TextBox();
                    inputNewISBN.setText(String.valueOf(b.getIsbnNumber()));
                    editBookPanel.addComponent(inputNewISBN.withBorder(Borders.singleLine()));

                    //Adding genres from checkBox
                    editBookPanel.addComponent(new Label("Choose genres: "));
                    CheckBoxList<Genre> checkBoxNewGenre = new CheckBoxList<Genre>();
                    for (Genre g : genres){
                        checkBoxNewGenre.addItem(g);
                        Genre findGenre = genreService.findByName(g.getName());
                        if(b.getGenres().contains(findGenre)){
                            checkBoxNewGenre.setChecked(g, true); // tutaj nie zaznacza
                        }
                    }
                    editBookPanel.addComponent(checkBoxNewGenre);

                    //Editing authors
                    editBookPanel.addComponent(new Label("Edit authors: "));
                    editBookPanel.addComponent(new Label(""));

                    for(Author a : b.getAuthors()){
                        editBookPanel.addComponent(new Label(a.toString()));
                        editBookPanel.addComponent(new Button("Remove", () -> {
                            a.getBooks().remove(b);
                            b.getAuthors().remove(a);
                            authorService.updateEntity(a);
                            MessageDialog.showMessageDialog(textGUI, "Removed author", a.toString() + " has been removed.", MessageDialogButton.OK);
                        }));
                    }

                    editBookPanel.addComponent(new Label("Add author"));
                    editBookPanel.addComponent(new Button("Add", () -> {

                        authorsToEditedBookPanel.addComponent(new Label("Enter author's name: "));
                        TextBox inputAuthorsName = new TextBox();
                        authorsToEditedBookPanel.addComponent(inputAuthorsName.withBorder(Borders.singleLine()));

                        authorsToEditedBookPanel.addComponent(new Label("Enter author's last name: "));
                        TextBox inputAuthorsLastName = new TextBox();
                        authorsToEditedBookPanel.addComponent(inputAuthorsLastName.withBorder(Borders.singleLine()));

                        authorsToEditedBookPanel.addComponent(new Label("Enter author's nationality: "));
                        TextBox inputAuthorsNationality = new TextBox();
                        authorsToEditedBookPanel.addComponent(inputAuthorsNationality.withBorder(Borders.singleLine()));

                        authorsToEditedBookPanel.addComponent(new Label("Enter author's date of birth (yyyy-mm-dd): "));
                        TextBox inputAuthorsDateOfBirth = new TextBox();
                        authorsToEditedBookPanel.addComponent(inputAuthorsDateOfBirth.withBorder(Borders.singleLine()));

                        authorsToEditedBookPanel.addComponent(new Button("Add", () -> {
                            Author author = new Author(inputAuthorsName.getText(), inputAuthorsLastName.getText(), LocalDate.parse(inputAuthorsDateOfBirth.getText()), inputAuthorsNationality.getText());
                            if(authorService.alreadyExists(author)){
                                MessageDialog.showMessageDialog(textGUI,"Warning", "This author already exists.", MessageDialogButton.OK);
                                Author existingAuthor = authorService.findTheSame(author);
                                b.getAuthors().add(existingAuthor);
                                //newAuthors.add(existingAuthor);
                            }else{
                                authorService.addEntity(author);
                                MessageDialog.showMessageDialog(textGUI,"Added author", "Author has been added.", MessageDialogButton.OK);
                                b.getAuthors().add(authorService.findTheSame(author));
                                //newAuthors.add(authorService.findTheSame(author));
                            }
                        }));

                        authorsToEditedBookPanel.addComponent(new Button("close", () -> textGUI.removeWindow(authorsToEditedBookWindow)));
                        textGUI.addWindowAndWait(authorsToEditedBookWindow);
                        authorsToEditedBookPanel.removeAllComponents();
                    }));
                    editBookPanel.addComponent(new Button("EDIT", () -> {
                        b.setTitle(inputNewTitle.getText());
                        b.setAmountAvailable(Integer.parseInt(inputNewAmount.getText()));
                        //Set<Author> newAuthorSet = new HashSet<>(newAuthors);
                        Set<Genre> newGenres = new HashSet<>(checkBoxNewGenre.getCheckedItems());
                        //b.setAuthors(newAuthorsSet);
                        for (Author a : b.getAuthors()){
                            a.getBooks().add(b);
                            authorService.updateEntity(a);
                        }
                        b.setGenres(newGenres);
                        b.setIsbnNumber(Long.parseLong(inputNewISBN.getText()));
                        bookService.updateEntity(b);
                        MessageDialog.showMessageDialog(textGUI,"Added author", "Book has been edited", MessageDialogButton.OK);
                    }));

                    editBookPanel.addComponent(new Button("close", () -> textGUI.removeWindow(editBookWindow)));
                    textGUI.addWindowAndWait(editBookWindow);
                    editBookPanel.removeAllComponents();
                }));
            }
            chooseBookToEditPanel.addComponent(new Button("close", () -> textGUI.removeWindow(chooseBookToEditWindow)));
            textGUI.addWindowAndWait(chooseBookToEditWindow);
            chooseBookToEditPanel.removeAllComponents();
        }));
        findBookToEditPanel.addComponent(new Button("close", () -> textGUI.removeWindow(findBookToEditWindow)));

        contentPanel.addComponent(new Label(""));
        contentPanel.addComponent(new Label(""));
        contentPanel.addComponent(new Label("READER'S CARD MANAGEMENT:"));
        contentPanel.addComponent(new Label(""));

        //ADD READER'S CARD
        final Window addReadersCardWindow = new BasicWindow("Add reader's card");

        Panel addReadersCardPanel = new Panel(new GridLayout(2));

        contentPanel.addComponent(new Label("Add reader's card"));
        contentPanel.addComponent(
                new Button("Add", () -> textGUI.addWindowAndWait(addReadersCardWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER)));

        addReadersCardPanel.addComponent(new Label("Enter reader's name: "));
        TextBox inputReadersName = new TextBox();
        addReadersCardPanel.addComponent(inputReadersName.withBorder(Borders.singleLine()));

        addReadersCardPanel.addComponent(new Label("Enter reader's last name: "));
        TextBox inputReadersLastName = new TextBox();
        addReadersCardPanel.addComponent(inputReadersLastName.withBorder(Borders.singleLine()));

        addReadersCardPanel.addComponent(new Label("Enter reader's PESEL number: "));
        TextBox inputReadersPesel = new TextBox();
        addReadersCardPanel.addComponent(inputReadersPesel.withBorder(Borders.singleLine()));

        addReadersCardPanel.addComponent(new Label("Enter reader's phone number: "));
        TextBox inputReadersPhone = new TextBox();
        addReadersCardPanel.addComponent(inputReadersPhone.withBorder(Borders.singleLine()));

        addReadersCardPanel.addComponent(new Label("Enter reader's date of birth (yyyy-mm-dd): "));
        TextBox inputReadersDateOfBirth = new TextBox();
        addReadersCardPanel.addComponent(inputReadersDateOfBirth.withBorder(Borders.singleLine()));

        addReadersCardPanel.addComponent(new Label("Enter reader's address (street, building number, local number): "));
        TextBox inputReadersAddress = new TextBox();
        addReadersCardPanel.addComponent(inputReadersAddress.withBorder(Borders.singleLine()));

        addReadersCardPanel.addComponent(new Button("Add", () -> {
            Reader reader = new Reader(inputReadersName.getText(), inputReadersLastName.getText(), Long.parseLong(inputReadersPesel.getText()), Long.parseLong(inputReadersPhone.getText()),
                    inputReadersAddress.getText(), LocalDate.parse(inputReadersDateOfBirth.getText()));
            if(readerService.alreadyExists(reader)){
                MessageDialog.showMessageDialog(textGUI,"Warning", "Reader already exists", MessageDialogButton.OK);
            }else {
                readerService.addEntity(reader);
                MessageDialog.showMessageDialog(textGUI,"Added reader", "Reader card has been added.", MessageDialogButton.OK);
            }
        }));
        addReadersCardPanel.addComponent(new Button("close", () -> textGUI.removeWindow(addReadersCardWindow)));

        //EDIT READER'S CARD
        final Window findReaderToEditWindow = new BasicWindow("Edit reader's card");
        final Window chooseReaderToEditWindow = new BasicWindow("Edit reader's card");
        final Window editReaderWindow = new BasicWindow("Edit reader's card");

        Panel findReaderToEditPanel = new Panel(new GridLayout(3));
        Panel chooseReaderToEditPanel = new Panel(new GridLayout(2));
        Panel editReaderPanel = new Panel(new GridLayout(2));

        contentPanel.addComponent(new Label("Edit reader's card"));
        contentPanel.addComponent(
                new Button("Edit", () -> textGUI.addWindowAndWait(findReaderToEditWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER)));

        findReaderToEditPanel.addComponent(new Label("Enter name of reader whose card you want to edit"));
        TextBox inputReaderToEdit = new TextBox();
        findReaderToEditPanel.addComponent(inputReaderToEdit);
        findReaderToEditPanel.addComponent(new Button("search", () -> {
            List<Reader> readers = readerService.findByName(inputReaderToEdit.getText());

            for(Reader r : readers){
                chooseReaderToEditPanel.addComponent(new Label(r.toString()));
                chooseReaderToEditPanel.addComponent(new Button("Edit", () -> {

                    editReaderPanel.addComponent(new Label("Edit reader's name: "));
                    TextBox inputNewReadersName = new TextBox();
                    inputNewReadersName.setText(r.getFirstname());
                    editReaderPanel.addComponent(inputNewReadersName.withBorder(Borders.singleLine()));

                    editReaderPanel.addComponent(new Label("Edit reader's last name: "));
                    TextBox inputNewReadersLastName = new TextBox();
                    inputNewReadersLastName.setText(r.getLastname());
                    editReaderPanel.addComponent(inputNewReadersLastName.withBorder(Borders.singleLine()));

                    editReaderPanel.addComponent(new Label("Edit reader's PESEL number: "));
                    TextBox inputNewReadersPesel = new TextBox();
                    inputNewReadersPesel.setText(r.getPeselNumber().toString());
                    editReaderPanel.addComponent(inputNewReadersPesel.withBorder(Borders.singleLine()));

                    editReaderPanel.addComponent(new Label("Edit reader's phone number: "));
                    TextBox inputNewReadersPhone = new TextBox();
                    inputNewReadersPhone.setText(r.getPhoneNumber().toString());
                    editReaderPanel.addComponent(inputNewReadersPhone.withBorder(Borders.singleLine()));

                    editReaderPanel.addComponent(new Label("Edit reader's date of birth (yyyy-mm-dd): "));
                    TextBox inputNewReadersDateOfBirth = new TextBox();
                    inputNewReadersDateOfBirth.setText(r.getBirthday().toString());
                    editReaderPanel.addComponent(inputNewReadersDateOfBirth.withBorder(Borders.singleLine()));

                    editReaderPanel.addComponent(new Label("Edit reader's address (street, building number, local number): "));
                    TextBox inputNewReadersAddress = new TextBox();
                    inputNewReadersAddress.setText(r.getAddress());
                    editReaderPanel.addComponent(inputNewReadersAddress.withBorder(Borders.singleLine()));

                    editReaderPanel.addComponent(new Button("Edit", () -> {
                        r.setFirstname(inputNewReadersName.getText());
                        r.setLastname(inputNewReadersLastName.getText());
                        r.setAddress(inputNewReadersAddress.getText());
                        r.setBirthday(LocalDate.parse(inputNewReadersDateOfBirth.getText()));
                        r.setPeselNumber(Long.parseLong(inputNewReadersPesel.getText()));
                        r.setPhoneNumber(Long.parseLong(inputNewReadersPhone.getText()));
                        readerService.updateEntity(r);
                        MessageDialog.showMessageDialog(textGUI,"Edited reader", "Reader card has been edited.", MessageDialogButton.OK);
                    }));
                    editReaderPanel.addComponent(new Button("close", () -> textGUI.removeWindow(editReaderWindow)));
                    textGUI.addWindowAndWait(editReaderWindow);
                    editReaderPanel.removeAllComponents();
                }));
            }
            chooseReaderToEditPanel.addComponent(new Button("close", () -> textGUI.removeWindow(chooseReaderToEditWindow)));
            textGUI.addWindowAndWait(chooseReaderToEditWindow);
            chooseReaderToEditPanel.removeAllComponents();
        }));
        findReaderToEditPanel.addComponent(new Button("close", () -> textGUI.removeWindow(findReaderToEditWindow)));

        //REMOVE READER'S CARD
        final Window findReaderToRemoveWindow = new BasicWindow("Remove reader's card");
        final Window chooseReaderToRemoveWindow = new BasicWindow("Remove reader's card");
        final Window removeReaderWindow = new BasicWindow("Remove reader's card");

        Panel findReaderToRemovePanel = new Panel(new GridLayout(3));
        Panel chooseReaderToRemovePanel = new Panel(new GridLayout(2));
        Panel removeReaderPanel = new Panel(new GridLayout(3));

        contentPanel.addComponent(new Label("Remove reader's card"));
        contentPanel.addComponent(
                new Button("Remove", () -> textGUI.addWindowAndWait(findReaderToRemoveWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER)));

        findReaderToRemovePanel.addComponent(new Label("Enter name of reader whose card you want to remove"));
        TextBox inputReaderToRemove = new TextBox();
        findReaderToRemovePanel.addComponent(inputReaderToRemove);
        findReaderToRemovePanel.addComponent(new Button("Search", () -> {
            List<Reader> readers = readerService.findByName(inputReaderToRemove.getText());

            for(Reader r : readers){
                chooseReaderToRemovePanel.addComponent(new Label(r.toString()));
                chooseReaderToRemovePanel.addComponent(new Button("Remove", () -> {
                    if(!readerService.checkedOutBooks(r).isEmpty()){
                        MessageDialog.showMessageDialog(textGUI,"Warning", "This reader has checked out books therefore their card cannot be removed.", MessageDialogButton.OK);
                    }else {
                        removeReaderPanel.addComponent(new Label("Are sure you want to remove card of " + r.getFirstname() + " " + r.getLastname() + "?"));
                        removeReaderPanel.addComponent(new Button("YES", () -> {
                            readerService.deleteEntity(r);
                            MessageDialog.showMessageDialog(textGUI,"Reader's card removed", "This reader has been removed.", MessageDialogButton.OK);
                            textGUI.removeWindow(removeReaderWindow);
                        }));
                        removeReaderPanel.addComponent(new Button("NO", () -> textGUI.removeWindow(removeReaderWindow)));

                        removeReaderPanel.addComponent(new Button("close", () -> textGUI.removeWindow(removeReaderWindow)));
                        textGUI.addWindowAndWait(removeReaderWindow);
                        removeReaderPanel.removeAllComponents();
                    }
                }));
            }
            chooseReaderToRemovePanel.addComponent(new Button("close", () -> textGUI.removeWindow(chooseReaderToRemoveWindow)));
            textGUI.addWindowAndWait(chooseReaderToRemoveWindow);
            chooseReaderToRemovePanel.removeAllComponents();
        }));
        findReaderToRemovePanel.addComponent(new Button("close", () -> textGUI.removeWindow(findReaderToRemoveWindow)));

        //CHECK IF READER IS ABLE TO CHECK OUT BOOK
        final Window findReaderToCheckWindow = new BasicWindow("Check reader's card");
        final Window checkReadersCardWindow = new BasicWindow("Check reader's card");

        Panel findReaderToCheckPanel = new Panel(new GridLayout(3));
        Panel checkReadersCardPanel = new Panel(new GridLayout(2));

        contentPanel.addComponent(new Label("Check reader's card"));
        contentPanel.addComponent(
                new Button("Check", () -> textGUI.addWindowAndWait(findReaderToCheckWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER)));

        findReaderToCheckPanel.addComponent(new Label("Enter name of reader whose card you want to check"));
        TextBox inputReaderToCheck = new TextBox();
        findReaderToCheckPanel.addComponent(inputReaderToCheck);
        findReaderToCheckPanel.addComponent(new Button("Check", () -> {
            List<Reader> readers = readerService.findByName(inputReaderToCheck.getText());

            for (Reader r : readers){
                checkReadersCardPanel.addComponent(new Label(r.toString()));
                checkReadersCardPanel.addComponent(new Button("Check", () -> {
                    if(readerService.checkedOutBooks(r).size() >= 4){
                        MessageDialog.showMessageDialog(textGUI,"Reader's card checked", "This reader already has 4 books. Can't check out more.", MessageDialogButton.OK);
                    }
                    else if(readerService.isOverdue(r)){
                        MessageDialog.showMessageDialog(textGUI,"Reader's card checked", "This reader has overdue book. Can't check out another one.", MessageDialogButton.OK);
                    }else{
                        MessageDialog.showMessageDialog(textGUI,"Reader's card checked", "This reader is able to check out "
                                + (4-readerService.checkedOutBooks(r).size()) + " more books.", MessageDialogButton.OK);
                    }
                }));
            }
            checkReadersCardPanel.addComponent(new Button("close", () -> textGUI.removeWindow(checkReadersCardWindow)));
            textGUI.addWindowAndWait(checkReadersCardWindow);
            checkReadersCardPanel.removeAllComponents();
        }));
        findReaderToCheckPanel.addComponent(new Button("close", () -> textGUI.removeWindow(findReaderToCheckWindow)));

        //SEARCH READER BY NAME
        final Window searchReaderWindow = new BasicWindow("Search Reader");
        contentPanel.addComponent(new Label("Search reader by name"));
        contentPanel.addComponent(
                new Button("Search", () -> textGUI.addWindowAndWait(searchReaderWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER))
        );

        Panel searchReaderPanel = new Panel(new GridLayout(3));

        searchReaderPanel.addComponent(new Label("Enter reader's name: "));
        TextBox inputReaderToFind = new TextBox();
        searchReaderPanel.addComponent(inputReaderToFind);
        searchReaderPanel.addComponent(new Button("Search", () -> {
            List<Reader> readers = readerService.findByName(inputReaderToFind.getText());

            if(readers.isEmpty()){
                MessageDialog.showMessageDialog(textGUI, "Found readers", "No reader was found.", MessageDialogButton.OK);
            }else {
                MessageDialog.showMessageDialog(textGUI, "Found readers", readerService.oneString(readers), MessageDialogButton.OK);
            }
        }));
        searchReaderPanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(searchReaderWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.END)));

        //CHECH IF SOMEONE IS OVERDUE
        final Window checkIfOverdueWindow = new BasicWindow("Check if overdue");
        Panel checkIfOverduePanel = new Panel(new GridLayout(1));

        contentPanel.addComponent(new Label("Check if someone if overdue"));
        contentPanel.addComponent(new Button("Check", () -> {
            List<Reader> readers = checkOutService.whoIsOverDue();

            for(Reader r : readers){
                checkIfOverduePanel.addComponent(new Label(r.toString()));
                checkIfOverduePanel.addComponent(new Label("Overdue books: " + bookService.titlesinOneString(readerService.overdueBooks(r))));
                checkIfOverduePanel.addComponent(new Label(""));
            }

            checkIfOverduePanel.addComponent(new Button("close", () -> textGUI.removeWindow(checkIfOverdueWindow)));
            textGUI.addWindowAndWait(checkIfOverdueWindow);
            checkIfOverduePanel.removeAllComponents();
        }));

        //SHOW READER'S BOOKS
        final Window checkReadersBooksWindow = new BasicWindow("Check reader's books");
        final Window readersBooksWindow = new BasicWindow("Check reader's books");

        Panel checkReadersBooksPanel = new Panel(new GridLayout(3));
        Panel readersBooksPanel = new Panel(new GridLayout(2));

        contentPanel.addComponent(new Label("Check reader's books"));
        contentPanel.addComponent(
                new Button("Check", () -> textGUI.addWindowAndWait(checkReadersBooksWindow)).setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,GridLayout.Alignment.CENTER)));

        checkReadersBooksPanel.addComponent(new Label("Enter name of reader to bo checked."));
        TextBox inputReader = new TextBox();
        checkReadersBooksPanel.addComponent(inputReader);
        checkReadersBooksPanel.addComponent(new Button("Check", () -> {
            List<Reader> readers = readerService.findByName(inputReader.getText());

            if(readers.isEmpty()){
                MessageDialog.showMessageDialog(textGUI, "Found readers", "No reader has been found.", MessageDialogButton.OK);
            }else {
                for(Reader r : readers){
                    readersBooksPanel.addComponent(new Label(r.toString()));
                    readersBooksPanel.addComponent(new Button("Check", () -> {
                        if(readerService.checkedOutBooks(r).isEmpty()){
                            MessageDialog.showMessageDialog(textGUI, "Found readers", "This reader has no checked out books.", MessageDialogButton.OK);
                        }else{
                            MessageDialog.showMessageDialog(textGUI, "Found readers",
                                    bookService.titlesinOneString(readerService.checkedOutBooks(r)), MessageDialogButton.OK);
                        }
                    }));
                    readersBooksPanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(readersBooksWindow)));
                    textGUI.addWindowAndWait(readersBooksWindow);
                    readersBooksPanel.removeAllComponents();
                }
            }
        }));
        checkReadersBooksPanel.addComponent(new Button("Close Window", () -> textGUI.removeWindow(checkReadersBooksWindow)));


        
        checkReadersBooksWindow.setComponent(checkReadersBooksPanel);
        readersBooksWindow.setComponent(readersBooksPanel);
        checkIfOverdueWindow.setComponent(checkIfOverduePanel);
        searchReaderWindow.setComponent(searchReaderPanel);
        findReaderToCheckWindow.setComponent(findReaderToCheckPanel);
        checkReadersCardWindow.setComponent(checkReadersCardPanel);
        findReaderToRemoveWindow.setComponent(findReaderToRemovePanel);
        chooseReaderToRemoveWindow.setComponent(chooseReaderToRemovePanel);
        removeReaderWindow.setComponent(removeReaderPanel);
        findReaderToEditWindow.setComponent(findReaderToEditPanel);
        chooseReaderToEditWindow.setComponent(chooseReaderToEditPanel);
        editReaderWindow.setComponent(editReaderPanel);
        addReadersCardWindow.setComponent(addReadersCardPanel);
        findBookToEditWindow.setComponent(findBookToEditPanel);
        chooseBookToEditWindow.setComponent(chooseBookToEditPanel);
        editBookWindow.setComponent(editBookPanel);
        authorsToEditedBookWindow.setComponent(authorsToEditedBookPanel);
        addBookWindow.setComponent(addBookPanel);
        addAuthorWindow.setComponent(addAuthorPanel);
        findReaderCheckingOutWindow.setComponent(findReaderCheckingOutPanel);
        chooseReaderCheckingOutWindow.setComponent(chooseReaderCheckingOutPanel);
        findBookToCheckOutWindow.setComponent(findBookToCheckOutPanel);
        chooseBookToCheckOutWindow.setComponent(chooseBookToCheckOutPanel);
        findReturningReaderWindow.setComponent(findReturningReaderPanel);
        chooseReturningReaderWindow.setComponent(chooseReturningReaderPanel);
        returnBookWindow.setComponent(returnBookPanel);
        whichToCheckWindow.setComponent(whichToCheckPanel);
        checkAvailabilityWindow.setComponent(checkAvailabilityPanel);
        removeBookByTitleWindow.setComponent(removeBookByTitlePanel);
        chooseBookToRemoveWindow.setComponent(chooseBookToRemovePanel);
        areYouSureToRemoveBookWindow.setComponent(areYouSureToRemoveBookPanel);
        searchBookByGenreWindow.setComponent(searchByGenrePanel);
        searchBookByTitleWindow.setComponent(searchByTitlePanel);
        searchBookByAuthorWindow.setComponent(searchByAuthorPanel);
        starterWindow.setComponent(contentPanel);
        textGUI.addWindowAndWait(starterWindow);


    }
}
