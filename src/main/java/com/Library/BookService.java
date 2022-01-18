package com.Library;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class BookService extends GenericDao<Book> {

    public List<Book> findByTitle(String title){
        List<Book> books = entityManager.createQuery("select b from Book as b where upper(b.title) like :title")
                .setParameter("title" , "%"+title.toUpperCase()+"%").getResultList();
        return books;
    }

    public List<Book> findByAuthors(String name){
        List<Book> books = entityManager.createQuery("select b from Book b " +
                "join b.authors a where upper(concat(a.firstname,' ', a.lastname)) like :name")
                .setParameter("name", "%"+name.toUpperCase()+"%").getResultList();
        return books;
    }

    public List<Book> findByGenre(String genre){
        List<Book> books = entityManager.createQuery("select  b from Book b join b.genres g where upper(g.name) like :genre")
                .setParameter("genre", "%"+genre.toUpperCase()+"%").getResultList();
        return books;
    }

    public Book findByISBN(Long ISBN){
        Book foundBook = (Book) entityManager.createQuery("select b from Book as b where b.isbnNumber = :ISBN").setParameter("ISBN", ISBN).getSingleResult();
        return foundBook;
    }

    public void checkOut(Book book, Reader reader){
        entityManager.getTransaction().begin();
        CheckOut checkOut = new CheckOut(book, reader, LocalDate.now(), LocalDate.now().plusMonths(1));
        entityManager.persist(checkOut);
        book.setAmountAvailable(book.getAmountAvailable()-1);
        entityManager.getTransaction().commit();
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
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Book b set b.amountAvailable = :amount where b.id = :bookId").setParameter("amount", book.getAmountAvailable()+1).setParameter("bookId", book.getId()).executeUpdate();
        entityManager.getTransaction().commit();
    }

    public void removeByTitle(String title){
        Book book = (Book) entityManager.createQuery("select b from Book b where b.title like :title").setParameter("title", "%"+title+"%").getSingleResult();
        //System.out.println(book.toString());
        deleteEntity(book);
    }

    public Boolean checkIfExists(Book book){
        List<Book> foundBook = entityManager.createQuery("select b from Book b where b.isbnNumber = :isbnNumber")
                .setParameter("isbnNumber", book.getIsbnNumber()).getResultList();

        if(foundBook.isEmpty()){
            return false;
        }else return true;
    }

    public Book clone (Book book){
        Book clone = (Book) entityManager.createQuery("select b from Book b where b.isbnNumber = :isbnNumber")
                .setParameter("isbnNumber", book.getTitle()).getSingleResult();
        return clone;
    }

    public String oneString(List<Book> books){
        String result = "";
        for (Book s:books) {
            result += s.toString() + " \n" ;
        }
        return result;
    }

    public String titlesinOneString(List<Book> books){
        String result = "";
        for (Book b : books) {
            result += b.getTitle() + " \n";
        }
        return result;
    }


    public List<Author> getAllAuthors(Book b){
        List<Author> authors = entityManager.createQuery("select a from Book b join b.authors a where b.id = :bookId").setParameter("bookId", b.getId()).getResultList();
        return authors;
    }

    public void deleteEntity(Book book){
        entityManager.getTransaction().begin();

        book.getAuthors().forEach(author -> {
            author.getBooks().remove(book);
            entityManager.merge(author);
        });

        if(entityManager.contains(book)) {
            entityManager.remove(book);
        }else{
            entityManager.remove(entityManager.merge(book));
        }
        entityManager.getTransaction().commit();

        //entityManager.close();
    }

    public boolean isCheckedOut(Book book){
        List<CheckOut> books = entityManager.createQuery("select c from CheckOut c join c.book b where b.isbnNumber = :isbn")
                .setParameter("isbn", book.getIsbnNumber()).getResultList();
        if(books.isEmpty()){
            return false;
        }else return true;
    }

    }


