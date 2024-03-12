/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao.post;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import com.mycompany.api.adapter.PostAdapter;
import com.mycompany.api.model.Post;
import com.mycompany.api.persistence.MongodbPojoPersistence;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author manuelmsni
 */
public class PostMongodbDAO implements PostDAO {
    
    private static PostMongodbDAO instance;
    
    private MongoCollection<PostAdapter> collection;

    private PostMongodbDAO() {
        this.collection = MongodbPojoPersistence.getCollection("api", "post", PostAdapter.class);
    }
    
    public static PostMongodbDAO getInstance(){
        if(instance == null) instance = new PostMongodbDAO();
        return instance;
    }

    @Override
    public List<PostAdapter> getAllPosts() {
        return MongodbPojoPersistence.getInstance().getAll(collection);
    }

    @Override
    public Post getPost(ObjectId id) {
        return MongodbPojoPersistence.getInstance().get(collection, id);
    }

    @Override
    public void insertPost(PostAdapter post) {
        MongodbPojoPersistence.getInstance().insert(collection, post);
    }
    
    @Override
    public List<Post> getPostsByUserId(int userId) {
        return collection.find(eq("userId", userId)).into(new ArrayList<>());
    }
    
}
