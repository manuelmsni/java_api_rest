/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.service;

import com.mycompany.api.dto.UserDTO;
import com.mycompany.api.model.User;

/**
 * Servicio para la gestión de usuarios. Proporciona funcionalidades de alto nivel
 * para interactuar con los datos de los usuarios, permitiendo la búsqueda y transformación
 * de estos en Data Transfer Objects (DTOs) para su uso en otras capas de la aplicación,
 * como endpoints de servicios REST o interfaces de usuario.
 * 
 * @author manuelmsni
 */
public interface UserServiceInterface {
    
    /**
     * Encuentra un DTO de usuario por su ID.
     * 
     * @param userId El ID del usuario.
     * @return Un UserDTO si el usuario es encontrado, null de lo contrario.
     */
    UserDTO findById(int userId);
    
    /**
     * Encuentra un DTO de usuario por su nombre de usuario.
     * 
     * @param username El nombre de usuario del usuario a encontrar.
     * @return Un UserDTO si el usuario es encontrado, null de lo contrario.
     */
    UserDTO findByUsername(String userName);
}
