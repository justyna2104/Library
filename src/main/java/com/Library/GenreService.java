package com.Library;

public class GenreService extends GenericDao<Genre> {

    public Genre findByName(String name){
        Genre genre = (Genre) entityManager.createQuery("select g from Genre as g where g.name like :name")
                .setParameter("name" , "%"+name+"%").getSingleResult();
        return genre;
    }
}
