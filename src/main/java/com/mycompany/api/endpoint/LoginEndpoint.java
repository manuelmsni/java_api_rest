/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.endpoint;

import com.mycompany.api.dao.user.UserDAO;
import com.mycompany.api.dao.user.UserHibernateDAO;
import com.mycompany.api.model.User;
import com.mycompany.api.util.JWTManager;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.mycompany.api.annotation.NoSessionRequired;
import com.mycompany.api.util.Hasher;

/**
 * REST Web Service
 *
 * @author manuelmsni
 */
@Path("login")
public class LoginEndpoint {

    private UserDAO userDAO = UserHibernateDAO.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @NoSessionRequired
    public Response loginUser(User credentials) {
        try {
            String password = Hasher.sha256(credentials.getPassword());
            User user = userDAO.findByEmailOrUsernameAndPassword(credentials.getEmail(), password);
            if (user != null) {
                String token = JWTManager.generateToken(user);
                return Response.ok("{\"token\":\"" + token + "\"}").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\":\"Credenciales inválidas\"}").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"Error al procesar la solicitud\"}").build();
        }
    }

}
