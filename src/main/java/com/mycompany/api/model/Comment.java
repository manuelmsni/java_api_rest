/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.model;

import org.bson.types.ObjectId;

/**
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
