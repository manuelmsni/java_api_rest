/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao.user;

import com.mycompany.api.model.User;

import com.mycompany.api.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

/**
 * 
 * @author manuelmsni
 */
public class UserHibernateDAO implements UserDAO{
    
    private static UserHibernateDAO instance;
    private EntityManagerFactory emf;

    private UserHibernateDAO() {
        emf = Persistence.createEntityManagerFactory("hbpu");
    }

    public static UserHibernateDAO getInstance(){
        if(instance == null) instance = new UserHibernateDAO();
        return instance;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (Exception e) {
            
            // TODO : Gestionar excepción
            
            return null;
        } finally {
            em.close();
        }
    }
    
    @Override
    public User findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }
    
    @Override
    public void insertUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void updateUser(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            User existingUser = em.find(User.class, user.getId());
            if (existingUser == null) {
                throw new Exception("Usuario no encontrado");
            }
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            
            // Asegúrate de hacer hash de la contraseña antes de llamar a updateUser si decides actualizar la contraseña aquí
            
            existingUser.setPassword(user.getPassword());

            em.merge(existingUser);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
}
