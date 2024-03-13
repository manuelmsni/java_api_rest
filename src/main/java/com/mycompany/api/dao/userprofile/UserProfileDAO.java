/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao.userprofile;

import com.mycompany.api.model.UserProfile;

/**
 * Interface para el acceso a datos del perfil del usuario. Ofrece métodos para la gestión
 * de perfiles de usuarios en la base de datos, incluyendo operaciones para recuperar,
 * insertar y actualizar perfiles de usuario.
 * 
 * @author manuelmsni
 */
public interface UserProfileDAO {
    
    /**
     * Recupera el perfil de un usuario específico.
     *
     * @param userId el ID del usuario cuyo perfil se desea recuperar
     * @return el perfil del usuario, o null si no existe
     */
     public UserProfile getUserProfile(int userId);
     
     /**
     * Inserta un nuevo perfil de usuario en la base de datos.
     *
     * @param profile el perfil del usuario a insertar
     */
     public void insertUserProfile(UserProfile profile);
     
     /**
     * Actualiza el perfil de un usuario existente.
     *
     * @param profile el perfil del usuario con los datos actualizados
     */
     public void updateUserProfile(UserProfile profile);
}
