/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author manuelmsni
 */
public class HibernatePersistence {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public HibernatePersistence(String persistenceUnitName){
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    }
    
    public EntityManager getEntityManager(){
        if(em == null) em = emf.createEntityManager();
        return em;
    }
    
    public void close(){
        if(em != null && em.isOpen()){
            em.close();
            em = null;
        }
    }

    public <T> T executeInsideTransaction(Function<EntityManager, T> function) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            T result = function.apply(em);
            em.getTransaction().commit();
            return result;
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            close();
        }
    }

    public <T> T find(Class<T> clazz, Object id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(clazz, id);
        } finally {
            close();
        }
    }

    public <T> List<T> findAll(Class<T> clazz) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("from " + clazz.getName(), clazz).getResultList();
        } finally {
            close();
        }
    }

    public <T> void save(T entity) {
        executeInsideTransaction(em -> {
            em.persist(entity);
            return entity; 
        });
    }

    public <T> T update(T entity) {
        return executeInsideTransaction(em -> em.merge(entity));
    }

    public <T> void delete(T entity) {
        executeInsideTransaction(em -> {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            return null; 
        });
    }
    
}
