package com.Library;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "id")
    private int id;

    @Column(name = "isbnNumber")
    private Long isbnNumber;

    @Column(name = "title")
    private String title;

    @ManyToMany (mappedBy = "books", cascade = {CascadeType.MERGE})
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "book_Genre",
            joinColumns = {@JoinColumn(name = "bookId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "genreId")}
            )
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<CheckOut> checkOuts;

    @Column(name = "amountAvailable")
    private int amountAvailable;

    public Book(String title, Long isbnNumber, Set<Author> authors, Set<Genre> genres, int amountAvailable) {
        this.title = title;
        this.isbnNumber = isbnNumber;
        this.authors = authors;
        this.genres = genres;
        this.amountAvailable = amountAvailable;
    }

    public Book(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public int getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(int amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public Long getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(Long isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public Set<CheckOut> getCheckOuts() {
        return checkOuts;
    }

    public void setCheckOuts(Set<CheckOut> checkOuts) {
        this.checkOuts = checkOuts;
    }

    @Override
    public String toString() {
        return "Book:" +
                " title: " + title +
                // " ISBN: " + isbnNumber +
                ", authors: " + authors +
                ", genres: " + genres +
                ", amountAvailable: " + amountAvailable;
    }
}
