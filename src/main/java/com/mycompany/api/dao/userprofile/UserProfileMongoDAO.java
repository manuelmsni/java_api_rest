/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao.userprofile;

import com.mongodb.client.MongoCollection;
import com.mycompany.api.model.UserProfile;
import com.mycompany.api.persistence.MongodbPojoPersistence;

/**
 * Clase encargada de gestionar las operaciones de acceso a datos para los perfiles de usuario en una base de datos MongoDB.
 * Ofrece métodos para la inserción, actualización y consulta de perfiles de usuario, permitiendo la manipulación de la
 * información de perfil asociada a cada usuario. Implementa el patrón Singleton para asegurar una única instancia de la
 * clase a lo largo de la ejecución de la aplicación, facilitando así la gestión centralizada de los perfiles de usuario.
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
