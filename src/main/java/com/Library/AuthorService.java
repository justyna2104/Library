package com.Library;

import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AuthorService extends GenericDao<Author> {

    public Boolean alreadyExists(Author author){

        List<Author> foundAuthor = entityManager.createQuery("select a from Author a where a.firstname = :firstname and a.lastname = :lastname and a.nationality =:nationality and a.dateOfBirth = :dateOfBirth")
                .setParameter("firstname", author.getFirstname()).setParameter("lastname", author.getLastname())
                .setParameter("nationality", author.getNationality()).setParameter("dateOfBirth", author.getDateOfBirth()).getResultList();

        if(foundAuthor.isEmpty()){
            return false;
        }else return true;
    }

    public Author findTheSame(Author author){
        Author clone = (Author) entityManager.createQuery("select a from Author a where a.firstname = :firstname and a.lastname = :lastname and a.nationality =:nationality and a.dateOfBirth = :dateOfBirth")
                .setParameter("firstname", author.getFirstname()).setParameter("lastname", author.getLastname())
                .setParameter("nationality", author.getNationality()).setParameter("dateOfBirth", author.getDateOfBirth()).getSingleResult();
        return clone;
    }

}
