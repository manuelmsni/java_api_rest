/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.api.endpoint;


import com.mycompany.api.dao.user.UserDAO;
import com.mycompany.api.dao.user.UserHibernateDAO;
import com.mycompany.api.model.User;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Endpoint para la búsqueda y recuperación de usuarios en la aplicación.
 * Permite realizar búsquedas de usuarios basadas en su nombre de usuario.
 *
 * @author manuelmsni
 */
@Path("users")
public class UsersEndpoint {

    private UserDAO userDAO = UserHibernateDAO.getInstance();

    /**
     * Busca usuarios cuyo nombre de usuario contenga el texto proporcionado.
     * Esta búsqueda es insensible a mayúsculas y minúsculas y permite la recuperación
     * de una lista de usuarios que coincidan con el criterio de búsqueda.
     *
     * @param username El texto de búsqueda utilizado para encontrar usuarios por nombre de usuario.
     * @return Una respuesta HTTP que contiene una lista de usuarios que coinciden con el criterio de búsqueda,
     *         o una respuesta indicando que no se encontraron coincidencias.
     */
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByUsername(@PathParam("username") String username) {
        List<User> users = userDAO.findUsersByUsernameLike(username);
        if (users != null && users.size() > 0) {
            return Response.ok(users).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Usuario no encontrado\"}").build();
        }
    }
    
}