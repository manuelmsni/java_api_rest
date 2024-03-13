/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao.comment;

import com.mycompany.api.model.Comment;
import com.mycompany.api.persistence.MongodbPojoPersistence;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Implementación de CommentDAO para MongoDB. Proporciona la lógica específica
 * para interactuar con una base de datos MongoDB, incluyendo la inserción, actualización
 * y eliminación de comentarios, así como la recuperación de comentarios basados en diversos
 * criterios como el ID del usuario y el contenido del comentario.
 * 
 * @author manuelmsni
 */
public class CommentMongodbDAO implements CommentDAO {
    
    private static CommentMongodbDAO instance;

    private MongoCollection<Comment> collection;

    private CommentMongodbDAO() {
        this.collection = MongodbPojoPersistence.getCollection("api", "post", Comment.class);
    }
    
    public static CommentMongodbDAO getInstance(){
        if(instance == null) instance = new CommentMongodbDAO();
        return instance;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void insertComment(ObjectId postId, Comment comment) {
        collection.updateOne(
            Filters.eq("_id", postId),
            Updates.push("comentarios", comment)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateComment(ObjectId postId, Comment comment) {
        collection.updateOne(
            Filters.and(
                Filters.eq("_id", postId),
                Filters.eq("comentarios._id", comment.getId())
            ),
            Updates.set("comentarios.$", comment)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteComment(ObjectId postId, ObjectId commentId) {
        collection.updateOne(
            Filters.eq("_id", postId),
            Updates.pull("comentarios", new Document("_id", commentId))
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Comment> getCommentsByUserId(int userId) {
        List<Bson> pipeline = List.of(
                Aggregates.match(Filters.eq("comentarios.userId", userId)), 
                Aggregates.unwind("$comentarios"), 
                Aggregates.match(Filters.eq("comentarios.userId", userId)), 
                Aggregates.project(Projections.fields(Projections.include("comentarios")))
        );
        return collection.aggregate(pipeline, Comment.class).into(new ArrayList<>());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Comment> getCommentsAfterDate(Date date) {
        List<Bson> pipeline = List.of(
            Aggregates.match(Filters.gt("comentarios.date", date)), 
            Aggregates.unwind("$comentarios"), 
            Aggregates.match(Filters.gt("comentarios.date", date)), 
            Aggregates.project(Projections.fields(Projections.include("comentarios")))
        );
        return collection.aggregate(pipeline, Comment.class).into(new ArrayList<>());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Comment> searchCommentsByContent(String search) {
        List<Bson> pipeline = List.of(
            Aggregates.match(Filters.regex("comentarios.content", search, "i")), 
            Aggregates.unwind("$comentarios"), 
            Aggregates.match(Filters.regex("comentarios.content", search, "i")), 
            Aggregates.project(Projections.fields(Projections.include("comentarios")))
        );
        return collection.aggregate(pipeline, Comment.class).into(new ArrayList<>());
    }
    
}
