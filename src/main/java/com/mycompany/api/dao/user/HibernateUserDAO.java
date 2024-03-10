/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao.user;

import com.mycompany.api.dao.user.UserDAO;
import com.mycompany.api.model.User;

/**
 *
 * @author manuelmsni
 */
public class HibernateUserDAO implements UserDAO{
    
    private static HibernateUserDAO instance;
    
    public static HibernateUserDAO getInstance(){
        if(instance == null) instance = new HibernateUserDAO();
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(int id) {
        return null;
    }
    
}
