/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.persistence;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mycompany.api.util.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

/**
 *
 * @author manuelmsni
 */
public class MongodbPojoPersistence {
    
    private static final String USERNAME = "root";
    private static final String PASSWORD = "docker";
    private static final String HOST = "mongodb";
    private static final String DB_URI = "mongodb://" + USERNAME + ":" + PASSWORD + "@" + HOST + ":" + Constants.MONGODB_PORT;
    
    private static MongodbPojoPersistence instance;
    
    private CodecRegistry codec;
    private MongoClient client;
    
    private MongodbPojoPersistence(){
        ConnectionString connectionString = new ConnectionString(DB_URI);
        client = MongoClients.create(connectionString);
        PojoCodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        codec = CodecRegistries.fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(), 
            CodecRegistries.fromProviders(pojoCodecProvider)
        );
    }
    
    public static MongodbPojoPersistence getInstance(){
        if(instance == null) instance = new MongodbPojoPersistence();
        return instance;
    }

    private MongoDatabase getDatabase(String dbname){
        return client.getDatabase(dbname).withCodecRegistry(codec);
    }
    
    public static <T> MongoCollection<T> getCollection(String database, String name, Class<T> type){
        return getInstance().getDatabase(database).getCollection(name, type);
    }
    
    // Métodos crud para tipos genéricos
    
    public static <T> List<T> getAll(MongoCollection<T> collection){
        List<T> list = new ArrayList<>();
        collection.find().into(list);
        return list;
    }
    
    public static <T> void insert(MongoCollection<T> collection, T obj) {
        collection.insertOne(obj);
    }
    
    public static <T> void insertMany(MongoCollection<T> collection, List<T> objects) {
        collection.insertMany(objects);
    }

    public static <T> void delete(MongoCollection<T> collection, ObjectId idToDelete) {
       collection.deleteOne(Filters.eq("_id", idToDelete));
    }
    
    public static <T> void update(MongoCollection<T> collection, ObjectId idToUpdate, T objToUpdate) {
       collection.replaceOne(Filters.eq("_id", idToUpdate), objToUpdate);
    }
    
    public static <T> T findById(MongoCollection<T> collection, ObjectId id) {
        return collection.find(Filters.eq("_id", id)).first();
    }
    
    public static <T> T findOneByField(MongoCollection<T> collection, String fieldName, Object value) {
        return collection.find(Filters.eq(fieldName, value)).first();
    }
    
    public static <T> List<T> findManyByField(MongoCollection<T> collection, String fieldName, Object value) {
        List<T> results = new ArrayList<>();
        collection.find(Filters.eq(fieldName, value)).into(results);
        return results;
    }
}
