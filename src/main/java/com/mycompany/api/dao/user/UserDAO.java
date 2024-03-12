/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao.user;

import com.mycompany.api.model.User;
import java.util.List;

/**
 *
 * @author manuelmsni
 */
public interface UserDAO {
    /**
     * Devuelve un usuario por su id
     * @param id El id de usuario
     * @return El usuario con dicha id
     */
    public User getUser(int id);
    
    public void insertUser(User user);

    public void updateUser(User user);
    
    public User findByEmailOrUsernameAndPassword(String email, String password);
    
    public User findByUsername(String username);
    
    public User findByEmail(String email);

    public List<User> findUsersByUsernameLike(String username);
}
