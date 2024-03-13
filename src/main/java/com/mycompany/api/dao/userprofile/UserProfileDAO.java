/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao.userprofile;

import com.mycompany.api.model.UserProfile;

/**
 *
 * @author manuelmsni
 */
public interface UserProfileDAO {
     public UserProfile getUserProfile(int userId);
     public void insertUserProfile(UserProfile profile);
     public void updateUserProfile(UserProfile profile);
}
