/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.service;

import com.mycompany.api.dao.user.UserDAO;
import com.mycompany.api.dao.user.UserHibernateDAO;
import com.mycompany.api.dao.userprofile.UserProfileDAO;
import com.mycompany.api.dao.userprofile.UserProfileMongoDAO;
import com.mycompany.api.dto.UserDTO;
import com.mycompany.api.model.User;
import com.mycompany.api.model.UserProfile;

/**
 * {@inheritDoc}
 * 
 * @author manuelmsni
 */
public class UserService implements UserServiceInterface {
    private UserDAO userDAO;
    private UserProfileDAO userProfileDAO;

    public UserService() {
        this.userDAO = UserHibernateDAO.getInstance();
        this.userProfileDAO = UserProfileMongoDAO.getInstance();
    }

    /**
     * Transforma un objeto {@link User} a un objeto {@link UserDTO}, incluyendo la
     * información del perfil del usuario obtenida a través de {@link UserProfileDAO}.
     * Este método centraliza la lógica de transformación, permitiendo su reutilización
     * en diferentes partes del servicio.
     * 
     * @param user El usuario a transformar a DTO.
     * @return Un objeto {@link UserDTO} con la información del usuario y su perfil,
     *         o null si el objeto {@link User} de entrada es null.
     */
    private UserDTO findByUser(User user){
        if (user != null){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            UserProfile userProfile = userProfileDAO.getUserProfile(user.getId());
            if(userProfile != null){
                userDTO.setDescription(userProfile.getDescription());
                userDTO.setProfileImage(userProfile.getProfileImage());
            }
            return userDTO;
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public UserDTO findById(int userId) {
        User user = userDAO.getUser(userId);
        return findByUser(user);
    }
    
    /**
     * {@inheritDoc}
     */
    public UserDTO findByUsername(String userName) {
        User user = userDAO.findByUsername(userName);
        return findByUser(user);
    }
    
}
