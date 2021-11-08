package com.Library;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {

        BookService bookService = new BookService();
        ReaderService readerService = new ReaderService();
        CheckOutService checkOutService = new CheckOutService();
        List<Reader> readers = checkOutService.whoIsOverDue();
        for (Reader r:readers) {
            System.out.println(r.toString());
        }

    }
}
