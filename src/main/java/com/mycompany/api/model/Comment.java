/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.model;

import org.bson.types.ObjectId;

/**
 * Representa un comentario realizado por un usuario en un post.
 * Cada comentario tiene asociado un ID único, el ID del usuario que lo realiza,
 * el contenido del comentario, el ID del post al cual está asociado, y opcionalmente,
 * un ID de respuesta si el comentario es una respuesta a otro comentario.
 * 
 * @author manuelmsni
 */
public class Comment {
    private ObjectId id;
    private int userId;
    private String content;
    private ObjectId postId; // Referencia al Post
    private ObjectId responseTo; // Autorreferencia a otro Comentario
    
    public Comment(){
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ObjectId getPostId() {
        return postId;
    }

    public void setPostId(ObjectId postId) {
        this.postId = postId;
    }

    public ObjectId getResponseTo() {
        return responseTo;
    }

    public void setResponseTo(ObjectId responseTo) {
        this.responseTo = responseTo;
    }
    
    
}
