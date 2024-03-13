/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao.post;

import com.mycompany.api.model.Post;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Interface para el acceso a datos de los posts. Proporciona métodos para la gestión
 * de posts en la base de datos, incluyendo operaciones de recuperación, inserción,
 * y recuperación de posts basados en el ID de usuario.
 * 
 * @author manuelmsni
 */
public interface PostDAO {
    
    /**
     * Recupera la lista de todos los posts existentes.
     *
     * @return una lista de todos los posts
     */
    public List<Post> getAllPosts();
    
    /**
     * Recupera un post específico por su ID.
     *
     * @param id el ID del post a recuperar
     * @return el post correspondiente al ID proporcionado
     */
    public Post getPost(ObjectId id);
    
    /**
     * Inserta un nuevo post en la base de datos.
     *
     * @param post el post a insertar
     */
    public void insertPost(Post post);
    
    /**
     * Recupera una lista de posts realizados por un usuario específico.
     *
     * @param userId el ID del usuario cuyos posts se desean recuperar
     * @return una lista de posts del usuario especificado
     */
    public List<Post> getPostsByUserId(int userId);
    
}
