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
     * Devuelve la lista de todos los posts
     * @return La lista de posts
     */
    public List<Post> getAllPosts();
    
    /**
     * Devuelve un post por su ObjectId
     * @param id El ObjectId de post
     * @return El post con dicha id
     */
    public Post getPost(ObjectId id);
    
    /**
     * 
     * @param post 
     */
    public void insertPost(Post post);
    
    public List<Post> getPostsByUserId(int userId);
    
}
