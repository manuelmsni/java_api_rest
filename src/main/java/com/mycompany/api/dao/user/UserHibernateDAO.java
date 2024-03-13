/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao.user;

import com.mycompany.api.model.User;

import com.mycompany.api.model.User;
import com.mycompany.api.persistence.HibernatePersistence;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author manuelmsni
 */
public class UserHibernateDAO implements UserDAO{
    
    private static UserHibernateDAO instance;
    private HibernatePersistence hp;

    private UserHibernateDAO() {
        hp = new HibernatePersistence("hbpu");
    }

    public static UserHibernateDAO getInstance(){
        if(instance == null) instance = new UserHibernateDAO();
        return instance;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void insertUser(User user) {
        hp.save(user);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(int id) {
        return hp.find(User.class, id);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUser(User user) {
        hp.update(user);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUser(User user) {
        hp.delete(user);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public User findByEmailOrUsernameAndPassword(String emailOrUsername, String password) {
        try {
            TypedQuery<User> query = hp.getEntityManager().createQuery("SELECT u FROM User u WHERE (u.email = :emailOrUsername OR u.username = :emailOrUsername) AND u.password = :password", User.class);
            query.setParameter("emailOrUsername", emailOrUsername);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
            // TODO : Gestionar excepción
            return null;
        } finally {
            hp.close();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public User findByEmail(String email) {
        try {
            TypedQuery<User> query = hp.getEntityManager().createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            hp.close();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public User findByUsername(String username) {
        try {
            return hp.getEntityManager().createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                     .setParameter("username", username)
                     .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            hp.close();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findUsersByUsernameLike(String username){
        try {
            TypedQuery<User> query = hp.getEntityManager().createQuery("SELECT u FROM User u WHERE u.username LIKE :usernamePattern", User.class);
            query.setParameter("usernamePattern", "%" + username + "%");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); 
        } finally {
            hp.close();
        }
    }
    
}
