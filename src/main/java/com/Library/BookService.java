package com.Library;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class BookService extends GenericDao<Book> {

    public List<Book> findByTitle(String title){
        List<Book> books = entityManager.createQuery("select b from Book as b where b.title like :title")
                .setParameter("title" , "%"+title+"%").getResultList();
        return books;
    }

    public List<Book> findByAuthors(String name){
        List<Book> books = entityManager.createQuery("select b from Book b " +
                "join b.authors a where concat(a.firstname,' ', a.lastname) like :name").setParameter("name", "%"+name+"%").getResultList();
        return books;
    }

    public List<Book> findByGenre(String genre){
        List<Book> books = entityManager.createQuery("select  b from Book b join b.genres g where g.name like :genre").setParameter("genre", "%"+genre+"%").getResultList();
        return books;
    }

    public void checkOut(Book book, Reader reader){
        CheckOut checkOut = new CheckOut(book, reader, LocalDate.now(), LocalDate.now().plusMonths(1));
        book.setAmountAvailable(book.getAmountAvailable()-1);
    }

    public Boolean checkAvailability(Book book){
        if(book.getAmountAvailable()>0){
            return true;
        }else return false;
    }

    public void handBack(Book book, Reader reader){
        CheckOut checkOut = (CheckOut) entityManager.createQuery("select c from CheckOut c join c.book b join c.reader r where r.id = :readerId and b.id = :bookId")
                .setParameter("readerId", reader.getId()).setParameter("bookId", book.getId()).getSingleResult();
        deleteEntity(checkOut);
        book.setAmountAvailable(book.getAmountAvailable()+1);
    }

    public void removeByTitle(String title){
        Book book = (Book) entityManager.createQuery("select b from Book b where b.title like :title").setParameter("title", "%"+title+"%").getSingleResult();
        System.out.println(book.toString());
        deleteEntity(book);
    }

}
