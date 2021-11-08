package com.Library;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "checkOut")
public class CheckOut {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;

    @Column(name = "chechOutDate")
    private LocalDate checkOutDate;

    @Column(name = "dueDate")
    private LocalDate dueDate;

    public CheckOut(Book book, Reader reader, LocalDate checkOutDate, LocalDate dueDate) {
        this.book = book;
        this.reader = reader;
        this.checkOutDate = checkOutDate;
        this.dueDate = dueDate;
    }

    public CheckOut(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "CheckOut: " +
                "book: " + book.getTitle().toString() +
                ", reader: " + reader.getFirstname() +
                ", reader: " + reader.getLastname() +
                ", checkOut date: " + checkOutDate +
                ", dueDate: " + dueDate;
    }
}
