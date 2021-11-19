package com.Library;

import javax.print.DocFlavor;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReaderService extends GenericDao<Reader> {

    public Boolean checkCheckOutAbility(Reader reader){
        Long amountOfBooks = (Long) entityManager.createQuery("select count(c) from CheckOut c where  c.reader.id = :readerId")
                .setParameter("readerId", reader.getId()).getSingleResult();
        if(amountOfBooks==4){
            return false;
        }else return true;
    }

    public List<Reader> findByName(String name){
        List<Reader> readers = entityManager.createQuery("select r from Reader r" +
                " where upper(concat(r.firstname,' ', r.lastname)) like :name").setParameter("name", "%"+name.toUpperCase()+"%").getResultList();
        return readers;
    }


    public List<Book> checkedOutBooks(Reader reader){
        List<Book> books = entityManager.createQuery("select b from Reader r join r.checkOuts c join c.book b " +
                "where r.id = :readerId").setParameter("readerId", reader.getId()).getResultList();
        return books;
    }

    public Boolean isOverdue(Reader reader){
        List<Reader> readers = entityManager.createQuery("select r from Reader r join r.checkOuts c where r.id = :id " +
                "and c.dueDate < :now").setParameter("id", reader.getId()).setParameter("now", LocalDate.now()).getResultList();

        if(readers.isEmpty()){
            return false;
        }else return true;
    }

    public Boolean alreadyExists(Reader reader){
        List<Reader> foundReader = entityManager.createQuery("select r from Reader r where r.peselNumber = :peselNumber")
                .setParameter("peselNumber", reader.getPeselNumber()).getResultList();

        if(foundReader.isEmpty()){
            return false;
        }else return true;
    }

    public Reader findByPesel(Long pesel){
        Reader reader = (Reader) entityManager.createQuery("select r from Reader r where r.peselNumber = :pesel")
                .setParameter("pesel", pesel).getSingleResult();
        return reader;
    }

    public String oneString(List<Reader> readers){
        String result = "";
        for (Reader r : readers) {
            result += r.toString() + " \n";
        }
        return result;
    }

    public List<Book> overdueBooks(Reader reader){
        List<Book> books = entityManager.createQuery("select b from Book b join b.checkOuts c join c.reader r" +
                " where r.id = :id and c.dueDate < :now").setParameter("id", reader.getId()).setParameter("now", LocalDate.now()).getResultList();
        return books;
    }

}
