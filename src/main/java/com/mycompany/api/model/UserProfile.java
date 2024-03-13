/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.model;

import org.bson.types.ObjectId;

/**
 * Representa el perfil de un usuario. 
 * Incluye información adicional sobre el usuario, como una descripción y una imagen de perfil.
 * Cada perfil está asociado a un usuario específico mediante un ID de usuario.
 * 
 * @author manuelmsni
 */
public class UserProfile {
    private ObjectId id;
    private int userId;
    private String description;
    private String profileImage;

    public UserProfile() {
    }
    

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    
}
