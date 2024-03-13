/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao.user;

import com.mycompany.api.model.User;
import java.util.List;

/**
 * Interface para el acceso a datos de los usuarios. Provee métodos para la gestión
 * de usuarios en la base de datos, incluyendo operaciones de inserción, actualización,
 * eliminación, y recuperación de usuarios basada en diferentes criterios.
 * 
 * @author manuelmsni
 */
public interface UserDAO {
    
    /**
     * Recupera un usuario por su ID.
     *
     * @param id el ID del usuario a recuperar
     * @return el usuario correspondiente al ID proporcionado
     */
    public User getUser(int id);
    
    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param user el usuario a insertar
     */
    public void insertUser(User user);

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param user el usuario con los datos actualizados
     */
    public void updateUser(User user);
    
    /**
     * Busca un usuario por email o nombre de usuario y contraseña.
     *
     * @param emailOrUsername el email o nombre de usuario
     * @param password la contraseña
     * @return el usuario que coincide con el email/nombre de usuario y contraseña
     */
    public User findByEmailOrUsernameAndPassword(String email, String password);
    
    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username el nombre de usuario a buscar
     * @return el usuario que coincide con el nombre de usuario proporcionado
     */
    public User findByUsername(String username);
    
    /**
     * Busca un usuario por su email.
     *
     * @param email el email a buscar
     * @return el usuario que coincide con el email proporcionado
     */
    public User findByEmail(String email);

    /**
     * Busca usuarios cuyo nombre de usuario contenga un texto específico.
     *
     * @param username el texto a buscar dentro del nombre de usuario
     * @return una lista de usuarios que coincidan con el criterio de búsqueda
     */
    public List<User> findUsersByUsernameLike(String username);
    
    /**
     * Elimina un usuario de la base de datos.
     *
     * @param user el usuario a eliminar
     */
    public void deleteUser(User user);
}
