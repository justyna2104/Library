package com.Library;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDao<T> {

    private Class<T> type;
    public EntityManager entityManager = CustomEntityManagerFactory.getEntityManager();

    public GenericDao() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public <T> void addEntity(T t){
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
        //entityManager.close();
    }


    public <T> List listEntity(){

        List<T> list = entityManager.createQuery("FROM " + type.getName()).getResultList();
        entityManager.close();
        return list;
    }

    public <T> T find(int id){

        Object object = null;
        entityManager.getTransaction().begin();
        object = (T) entityManager.find(type, id);
        entityManager.getTransaction().commit();
        //entityManager.close();
        return (T) object;
    }

    public <T> void deleteEntity(T t){

        entityManager.getTransaction().begin();
        if(entityManager.contains(t)) {
            entityManager.remove(t);
        }else{
            entityManager.remove(entityManager.merge(t));
        }
        entityManager.getTransaction().commit();

        //entityManager.close();
    }

    public <T> void updateEntity(T t){

        entityManager.getTransaction().begin();
        entityManager.merge(t);
        entityManager.getTransaction().commit();
        //entityManager.close();
    }

}
