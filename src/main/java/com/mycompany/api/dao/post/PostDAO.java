/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao.post;

import com.mycompany.api.adapter.PostAdapter;
import com.mycompany.api.model.Post;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author manuelmsni
 */
public interface PostDAO {
    /**
     * Devuelve la lista de todos los posts
     * @return La lista de posts
     */
    public List<PostAdapter> getAllPosts();
    
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
    public void insertPost(PostAdapter post);
    
}
