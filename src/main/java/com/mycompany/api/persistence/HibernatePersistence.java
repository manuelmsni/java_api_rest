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
 * Clase que facilita la interacción con la base de datos usando el framework Hibernate.
 * Proporciona métodos simplificados para realizar operaciones CRUD y transacciones,
 * encapsulando la complejidad de gestionar la EntityManagerFactory y EntityManager.
 * 
 * @author manuelmsni
 */
public class HibernatePersistence {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public HibernatePersistence(String persistenceUnitName){
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    }
    
    /**
     * Obtiene una instancia de EntityManager, asegurando que solo se cree una por solicitud.
     * 
     * @return Una instancia de EntityManager.
     */
    public EntityManager getEntityManager(){
        if(em == null) em = emf.createEntityManager();
        return em;
    }
    
    /**
     * Cierra el EntityManager actual si está abierto, para liberar recursos.
     */
    public void close(){
        if(em != null && em.isOpen()){
            em.close();
            em = null;
        }
    }

    /**
     * Ejecuta una función dentro de una transacción de base de datos.
     * Maneja la apertura y cierre de la transacción, así como el commit o rollback según corresponda.
     * 
     * @param <T> El tipo de retorno de la función.
     * @param function La función a ejecutar, que recibe un EntityManager y devuelve un valor de tipo T.
     * @return El valor devuelto por la función.
     */
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

    /**
     * Encuentra una entidad por su clase y su ID.
     * 
     * @param <T> El tipo de la entidad.
     * @param clazz La clase de la entidad.
     * @param id El ID de la entidad a buscar.
     * @return La entidad encontrada, o null si no existe.
     */
    public <T> T find(Class<T> clazz, Object id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(clazz, id);
        } finally {
            close();
        }
    }

    /**
     * Recupera todas las instancias de una entidad.
     * 
     * @param <T> El tipo de la entidad.
     * @param clazz La clase de la entidad.
     * @return Una lista de todas las instancias de la entidad.
     */
    public <T> List<T> findAll(Class<T> clazz) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("from " + clazz.getName(), clazz).getResultList();
        } finally {
            close();
        }
    }

    /**
     * Persiste una entidad en la base de datos.
     * 
     * @param <T> El tipo de la entidad.
     * @param entity La entidad a persistir.
     */
    public <T> void save(T entity) {
        executeInsideTransaction(em -> {
            em.persist(entity);
            return entity; 
        });
    }

    /**
     * Actualiza una entidad en la base de datos.
     * 
     * @param <T> El tipo de la entidad.
     * @param entity La entidad a actualizar.
     * @return La entidad actualizada.
     */
    public <T> T update(T entity) {
        return executeInsideTransaction(em -> em.merge(entity));
    }

    /**
     * Elimina una entidad de la base de datos.
     * 
     * @param <T> El tipo de la entidad.
     * @param entity La entidad a eliminar.
     */
    public <T> void delete(T entity) {
        executeInsideTransaction(em -> {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            return null; 
        });
    }
    
}
