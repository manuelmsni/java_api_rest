/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dto;

/**
 *
 * @author manuelmsni
 */
public class UserDTO {
    
    private int id;
    private String username;
    private String email;
    private String description;
    private String profileImage;

    public UserDTO() {
    }

    public UserDTO(int id, String username, String email, String description, String profileImage) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.description = description;
        this.profileImage = profileImage;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
