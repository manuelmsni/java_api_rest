/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.endpoint;

import com.mycompany.api.adapter.PostAdapter;
import com.mycompany.api.annotation.NoSessionRequired;
import com.mycompany.api.dao.post.PostDAO;
import com.mycompany.api.dao.post.PostMongodbDAO;
import com.mycompany.api.model.Post;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.types.ObjectId;

/**
 *
 * @author manuelmsni
 */
@Path("post")
public class PostEndpoint {

    private final PostDAO postDAO = PostMongodbDAO.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @NoSessionRequired
    public Response getAllPosts() {
        try {
            return Response.ok(postDAO.getAllPosts()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @NoSessionRequired
    public Response getPost(@PathParam("id") String id) {
        try {
            Post post = postDAO.getPost(new ObjectId(id));
            if (post == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Post no encontrado\"}").build();
            }
            return Response.ok(post).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPost(PostAdapter post) {
        try {
            postDAO.insertPost(post);
            return Response.status(Response.Status.CREATED).entity(post).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"Error al crear el post\"}").build();
        }
    }

}
