/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.endpoint;

import com.mycompany.api.util.JWTManager;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author manuelmsni
 */
@Path("session")
public class SessionEndpoint {

    @GET
    public Response verifySession(@HeaderParam("Authorization") String authToken) {
        if (authToken == null || !authToken.startsWith("Bearer ")) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\":\"No se proporcionó un token de autorización válido.\"}").build();
        }

        String jwtToken = authToken.substring(7); // Elimina "Bearer " del inicio del token

        if (JWTManager.verifyToken(jwtToken)) {
            return Response.ok("{\"message\":\"La sesión es válida.\"}").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\":\"La sesión no es válida o el token ha expirado.\"}").build();
        }
    }
}