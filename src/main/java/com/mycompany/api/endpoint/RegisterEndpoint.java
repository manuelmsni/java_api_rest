/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.endpoint;

import com.mycompany.api.dao.user.UserDAO;
import com.mycompany.api.dao.user.UserHibernateDAO;
import com.mycompany.api.model.User;
import com.mycompany.api.util.Hasher;
import com.mycompany.api.annotation.NoSessionRequired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Endpoint para registrar nuevos usuarios en la aplicación.
 * Provee la funcionalidad para que los usuarios se puedan registrar proporcionando
 * su información básica como el nombre de usuario, correo electrónico y contraseña.
 * 
 * @author manuelmsni
 */
@Path("register")
public class RegisterEndpoint {

    private UserDAO userDAO = UserHibernateDAO.getInstance();

    /**
     * Permite a un nuevo usuario registrarse en la aplicación. La contraseña del usuario
     * se hashea antes de almacenarla en la base de datos para garantizar la seguridad.
     * 
     * @param user Un objeto {@link User} conteniendo la información del usuario a registrar.
     * @return Una respuesta HTTP indicando éxito y un mensaje de confirmación; o una respuesta de error
     * si el registro no se puede completar, por ejemplo, si el correo electrónico ya está en uso.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @NoSessionRequired
    public Response registerUser(User user) {
        try {
            
            if (userDAO.findByEmail(user.getEmail()) != null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"El correo electrónico ya está en uso.\"}").build();
            }
            
            String hashedPassword = Hasher.sha256(user.getPassword());
            user.setPassword(hashedPassword);
            
            userDAO.insertUser(user);
            
            return Response.ok("{\"message\":\"Usuario registrado exitosamente.\"}").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"Error al registrar el usuario.\"}").build();
        }
    }
}
