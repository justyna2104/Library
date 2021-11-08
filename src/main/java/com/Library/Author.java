package com.Library;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "id")
    private int id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "nationality")
    private String nationality;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "author_book",
            joinColumns = {@JoinColumn(name = "authorId")},
            inverseJoinColumns = {@JoinColumn(name = "bookId")}
    )
    private Set<Book> books = new HashSet<>();

    public Author(String firstname, String lastname, String nationality, Set<Book> books) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationality = nationality;
        this.books = books;
    }

    public Author(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author: " +
                "firstname: " + firstname +
                ", lastname: " + lastname +
                ", nationality: " + nationality +
                ", books: " + books;
    }
}
