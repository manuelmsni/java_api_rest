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
 * Endpoint para la verificación de la validez de la sesión de usuario.
 * Provee una operación para verificar si el token JWT incluido en las solicitudes
 * es válido y aún no ha expirado, lo cual es crucial para mantener la seguridad
 * y la integridad de la sesión del usuario.
 * 
 * @author manuelmsni
 */
@Path("session")
public class SessionEndpoint {

    /**
     * Verifica la validez del token JWT proporcionado en el encabezado de autorización
     * de una solicitud HTTP. Esta verificación es esencial para las operaciones que requieren
     * que el usuario esté autenticado.
     *
     * @param authToken El token JWT proporcionado en el encabezado de autorización.
     * @return Una respuesta HTTP con un mensaje indicando si la sesión es válida o no.
     *         Retorna un estado de autorización fallida si el token es inválido o ha expirado.
     */
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