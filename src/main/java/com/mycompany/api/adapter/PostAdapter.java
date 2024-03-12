/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.adapter;

import com.mycompany.api.model.Post;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author manuelmsni
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostAdapter extends Post {
    
    private String postId;
    
    public PostAdapter(){
        super();
    }
    
    public String getPostId() {
        return getId().toHexString();
    }
    
}
