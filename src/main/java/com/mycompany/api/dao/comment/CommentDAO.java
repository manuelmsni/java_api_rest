/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.dao.comment;

import com.mycompany.api.model.Comment;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Interface para el acceso a datos de los comentarios. Provee métodos para la gestión
 * de comentarios en la base de datos, incluyendo operaciones de inserción, actualización,
 * y eliminación, así como la recuperación de comentarios basada en diferentes criterios.
 *
 * @author manuelmsni
 */
public interface CommentDAO {
    
    /**
     * Inserta un nuevo comentario asociado a un post específico.
     *
     * @param postId el ID del post al que el comentario pertenece
     * @param comment el comentario a insertar
     */
    void insertComment(ObjectId postId, Comment comment);
    
    /**
     * Actualiza un comentario existente asociado a un post específico.
     *
     * @param postId el ID del post al que el comentario pertenece
     * @param comment el comentario con los datos actualizados
     */
    void updateComment(ObjectId postId, Comment comment);
    
    /**
     * Elimina un comentario específico de un post.
     *
     * @param postId el ID del post al que el comentario pertenece
     * @param commentId el ID del comentario a eliminar
     */
    void deleteComment(ObjectId postId, ObjectId commentId);
    
    /**
     * Recupera una lista de comentarios realizados por un usuario específico.
     *
     * @param userId el ID del usuario cuyos comentarios se desean recuperar
     * @return una lista de comentarios
     */
    List<Comment> getCommentsByUserId(int userId);
    
    /**
     * Recupera una lista de comentarios hechos después de una fecha específica.
     *
     * @param date la fecha a partir de la cual recuperar los comentarios
     * @return una lista de comentarios
     */
    List<Comment> getCommentsAfterDate(Date date);
    
    /**
     * Busca comentarios que contengan un texto específico en su contenido.
     *
     * @param search el texto a buscar dentro del contenido de los comentarios
     * @return una lista de comentarios que coincidan con el criterio de búsqueda
     */
    List<Comment> searchCommentsByContent(String search);
}
