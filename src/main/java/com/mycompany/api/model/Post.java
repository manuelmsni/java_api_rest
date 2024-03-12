/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.model;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author manuelmsni
 */
public class Post {
    private ObjectId id; // MongoDB ObjectID
    private int userId;
    private ObjectId responseTo; // Autorreferencia a otro Post
    private String content;
    private List<String> imageUrls; // URLs de las imágenes
    private List<Comment> comentarios; // Comentarios del post
    
    public Post(){
        imageUrls = new ArrayList<>();
        comentarios = new ArrayList<>();
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

    public ObjectId getResponseTo() {
        return responseTo;
    }

    public void setResponseTo(ObjectId responseTo) {
        this.responseTo = responseTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<Comment> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comment> comentarios) {
        this.comentarios = comentarios;
    }
    
    
}
