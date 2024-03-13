/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao.post;

import com.mongodb.client.MongoCollection;
import com.mycompany.api.model.Post;
import com.mycompany.api.persistence.MongodbPojoPersistence;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author manuelmsni
 */
public class PostMongodbDAO implements PostDAO {
    
    private static PostMongodbDAO instance;
    
    private MongoCollection<Post> collection;

    private PostMongodbDAO() {
        this.collection = MongodbPojoPersistence.getCollection("api", "post", Post.class);
    }
    
    public static PostMongodbDAO getInstance(){
        if(instance == null) instance = new PostMongodbDAO();
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getAllPosts() {
        return MongodbPojoPersistence.getInstance().getAll(collection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Post getPost(ObjectId id) {
        return MongodbPojoPersistence.getInstance().findById(collection, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertPost(Post post) {
        MongodbPojoPersistence.getInstance().insert(collection, post);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getPostsByUserId(int userId) {
        return MongodbPojoPersistence.getInstance().findManyByField(collection, "userId", userId);
    }
    
}
