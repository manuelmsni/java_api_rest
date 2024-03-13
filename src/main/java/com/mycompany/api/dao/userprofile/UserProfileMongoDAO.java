/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao.userprofile;

import com.mongodb.client.MongoCollection;
import com.mycompany.api.model.UserProfile;
import com.mycompany.api.persistence.MongodbPojoPersistence;

/**
 *
 * @author manuelmsni
 */
public class UserProfileMongoDAO implements UserProfileDAO {
    
    private static UserProfileMongoDAO instance;
    
    private MongoCollection<UserProfile> collection;

    private UserProfileMongoDAO() {
        this.collection = MongodbPojoPersistence.getCollection("api", "userprofile", UserProfile.class);
    }
    
    public static UserProfileMongoDAO getInstance(){
        if(instance == null) instance = new UserProfileMongoDAO();
        return instance;
    }
    
    /**
     * {@inheritDoc}
     */
    public UserProfile getUserProfile(int userId) {
        return MongodbPojoPersistence.findOneByField(collection, "userId", userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertUserProfile(UserProfile profile) {
        MongodbPojoPersistence.insert(collection, profile);
    }
    
    /**
     * {@inheritDoc}
     */
    public void updateUserProfile(UserProfile profile) {
        MongodbPojoPersistence.update(collection, profile.getId(), profile);
    }
    
}
