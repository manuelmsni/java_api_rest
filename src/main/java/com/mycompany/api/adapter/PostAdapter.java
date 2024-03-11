/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.adapter;

import com.mycompany.api.model.Post;

/**
 *
 * @author manuelmsni
 */
public class PostAdapter extends Post {
    
    private String postId;
    
    public String getPostId() {
        return getId().toHexString();
    }
    
}
