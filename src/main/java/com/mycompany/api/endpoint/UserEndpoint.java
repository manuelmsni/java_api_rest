/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.api.endpoint;


import com.mycompany.api.adapter.PostAdapter;
import com.mycompany.api.dao.post.PostDAO;
import com.mycompany.api.dao.post.PostMongodbDAO;
import com.mycompany.api.dao.user.UserDAO;
import com.mycompany.api.dao.user.UserHibernateDAO;
import com.mycompany.api.model.Post;
import com.mycompany.api.model.User;
import com.mycompany.api.util.Hasher;
import com.mycompany.api.util.JWTManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author manuelmsni
 */
@Path("user")
public class UserEndpoint {

    private UserDAO userDAO = UserHibernateDAO.getInstance();
    private PostDAO postDAO = PostMongodbDAO.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDetails(@HeaderParam("Authorization") String authToken) {
        
        User user = JWTManager.getUserFromToken(authToken);
        
        return Response.ok("{\"username\":\"" + user.getUsername() + "\"}").build();
        
    }
    
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByUsername(@PathParam("username") String username) {
        User user = userDAO.findByUsername(username);
        if (user != null) {
            List<Post> posts = postDAO.getPostsByUserId(user.getId());
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("posts", posts);
            return Response.ok(response).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Usuario no encontrado\"}").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user, @HeaderParam("Authorization") String authToken) {
        try {
            User tokenUser = JWTManager.getUserFromToken(authToken);
            
            if (user.getId() != tokenUser.getId()) {
                return Response.status(Response.Status.FORBIDDEN).entity("{\"error\":\"No tiene permiso para actualizar este usuario\"}").build();
            }
            
            if (userDAO.findByEmail(user.getEmail()) != null && userDAO.findByEmail(user.getEmail()).getId() != user.getId()) {
                return Response.status(Response.Status.CONFLICT).entity("{\"error\":\"El correo electrónico ya está en uso.\"}").build();
            }

            user.setPassword(Hasher.sha256(user.getPassword()));
            userDAO.updateUser(user);
            
            return Response.ok().entity("{\"message\":\"Usuario actualizado exitosamente.\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"Error al actualizar el usuario.\"}").build();
        }
    }
}