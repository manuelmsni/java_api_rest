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
import com.mongodb.client.model.Filters;
import com.mycompany.api.util.Constants;
import java.util.ArrayList;
import java.util.List;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

/**
 * Clase encargada de la persistencia de POJOs en MongoDB.
 * Utiliza el driver de MongoDB para Java y ofrece una interfaz simplificada para realizar operaciones
 * CRUD sobre colecciones de documentos, tratando directamente con clases Java (POJOs) en lugar de con
 * documentos BSON. Para ello, hace uso del soporte de POJO del driver mediante CodecRegistries.
 * 
 * Esta clase implementa un patrón singleton para asegurar una única instancia del cliente de MongoDB
 * a lo largo de la aplicación. Proporciona métodos estáticos para operaciones comunes como insertar,
 * buscar y eliminar documentos, abstrayendo los detalles de conexión y configuración del cliente de MongoDB.
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
    
    /**
     * Obtiene una colección de MongoDB configurada para trabajar con un tipo específico de POJO.
     * Utiliza el {@link CodecRegistry} configurado para permitir la serialización y deserialización
     * automática de los POJOs a documentos BSON y viceversa.
     * 
     * @param database El nombre de la base de datos en MongoDB.
     * @param name El nombre de la colección dentro de la base de datos.
     * @param type La clase del POJO con la que la colección trabajará.
     * @return Una {@link MongoCollection} configurada para trabajar con el tipo de POJO especificado.
     */
    public static <T> MongoCollection<T> getCollection(String database, String name, Class<T> type){
        return getInstance().getDatabase(database).getCollection(name, type);
    }
    
    /**
     * Recupera todos los objetos de una colección específica.
     * Convierte los documentos BSON recuperados en objetos del tipo especificado.
     * 
     * @param collection La colección de la cual se quieren recuperar todos los objetos.
     * @return Una lista de objetos del tipo especificado contenidos en la colección.
     */
    public static <T> List<T> getAll(MongoCollection<T> collection){
        List<T> list = new ArrayList<>();
        collection.find().into(list);
        return list;
    }
    
    /**
     * Inserta un objeto (POJO) en la colección especificada.
     * Este método convierte el POJO en un documento BSON para su almacenamiento en MongoDB.
     * 
     * @param collection La colección en la que se insertará el objeto.
     * @param obj El objeto a insertar.
     */
    public static <T> void insert(MongoCollection<T> collection, T obj) {
        collection.insertOne(obj);
    }
    
    /**
     * Inserta múltiples objetos (POJOs) en la colección especificada.
     * Convierte cada POJO en un documento BSON para su almacenamiento en MongoDB.
     * 
     * @param collection La colección en la que se insertarán los objetos.
     * @param objects Los objetos a insertar en la colección.
     */
    public static <T> void insertMany(MongoCollection<T> collection, List<T> objects) {
        collection.insertMany(objects);
    }

    /**
     * Elimina un objeto de la colección especificada por su ID.
     * 
     * @param collection La colección de la que se eliminará el objeto.
     * @param idToDelete El ID del objeto a eliminar.
     */
    public static <T> void delete(MongoCollection<T> collection, ObjectId idToDelete) {
       collection.deleteOne(Filters.eq("_id", idToDelete));
    }
    
    /**
     * Actualiza un objeto existente en la colección especificada por su ID.
     * Reemplaza el documento BSON existente con una nueva versión generada a partir del POJO proporcionado.
     * 
     * @param collection La colección en la que se actualizará el objeto.
     * @param idToUpdate El ID del objeto a actualizar.
     * @param objToUpdate El nuevo objeto que reemplazará al existente.
     */
    public static <T> void update(MongoCollection<T> collection, ObjectId idToUpdate, T objToUpdate) {
       collection.replaceOne(Filters.eq("_id", idToUpdate), objToUpdate);
    }
    
    /**
     * Busca y retorna un objeto por su ID en la colección especificada.
     * Convierte el documento BSON encontrado en un objeto del tipo especificado.
     * 
     * @param collection La colección en la que se buscará el objeto.
     * @param id El ID del objeto a buscar.
     * @return El objeto encontrado, o null si no existe un objeto con ese ID.
     */
    public static <T> T findById(MongoCollection<T> collection, ObjectId id) {
        return collection.find(Filters.eq("_id", id)).first();
    }
    
    /**
     * Busca y retorna el primer objeto que coincida con el valor de un campo específico en la colección.
     * Convierte el documento BSON encontrado en un objeto del tipo especificado.
     * 
     * @param collection La colección en la que se buscará el objeto.
     * @param fieldName El nombre del campo por el cual realizar la búsqueda.
     * @param value El valor del campo a buscar.
     * @return El primer objeto encontrado que coincide con el criterio de búsqueda, o null si no se encuentra ninguno.
     */
    public static <T> T findOneByField(MongoCollection<T> collection, String fieldName, Object value) {
        return collection.find(Filters.eq(fieldName, value)).first();
    }
    
    /**
     * Busca y retorna todos los objetos que coincidan con el valor de un campo específico en la colección.
     * Convierte los documentos BSON encontrados en objetos del tipo especificado.
     * 
     * @param collection La colección en la que se realizará la búsqueda.
     * @param fieldName El nombre del campo por el cual realizar la búsqueda.
     * @param value El valor del campo a buscar.
     * @return Una lista de objetos que coinciden con el criterio de búsqueda.
     */
    public static <T> List<T> findManyByField(MongoCollection<T> collection, String fieldName, Object value) {
        List<T> results = new ArrayList<>();
        collection.find(Filters.eq(fieldName, value)).into(results);
        return results;
    }
}
