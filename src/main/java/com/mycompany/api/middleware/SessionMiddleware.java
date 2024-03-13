/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.middleware;

import javax.ws.rs.container.ResourceInfo;
import com.mycompany.api.annotation.NoSessionRequired;
import com.mycompany.api.dao.user.UserHibernateDAO;
import com.mycompany.api.model.User;
import com.mycompany.api.util.JWTManager;
import io.jsonwebtoken.Claims;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Middleware que intercepta las solicitudes HTTP entrantes para verificar
 * la presencia y validez de un token JWT en los encabezados de autorización.
 * Este filtro asegura que solo las solicitudes autenticadas o marcadas explícitamente
 * como {@link NoSessionRequired} puedan acceder a los recursos protegidos.
 * 
 * @author manuelmsni
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class SessionMiddleware implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    /**
     * Método invocado automáticamente antes de que una solicitud llegue a un recurso REST.
     * Verifica si el recurso o método solicitado requiere autenticación y, en caso afirmativo,
     * valida el token JWT proporcionado en el encabezado de autorización.
     * 
     * @param requestContext Contexto de la solicitud HTTP que contiene información sobre la misma.
     * @throws IOException Si ocurre un error de entrada/salida durante el procesamiento del filtro.
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        boolean noAuthRequired = resourceInfo.getResourceMethod().isAnnotationPresent(NoSessionRequired.class)
                || resourceInfo.getResourceClass().isAnnotationPresent(NoSessionRequired.class);
        if (noAuthRequired) {
            return;
        }
        String token = requestContext.getHeaderString("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }
        String jwtToken = token.substring(7); // Ignore "Bearer"
        User user = getSessionUser(jwtToken);
        if (user == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }
    }

    /**
     * Obtiene el usuario asociado a un token JWT, verificando su validez y extrayendo
     * el ID del usuario contenido en el token. Si el token es válido y el usuario existe,
     * retorna el objeto {@link User} correspondiente.
     * 
     * @param jwtToken El token JWT a decodificar y verificar.
     * @return El usuario asociado al token JWT, o null si el token es inválido o el usuario no existe.
     */
    private User getSessionUser(String jwtToken) {
        JWTManager.verifyToken(jwtToken);
        Claims claims = JWTManager.decodeToken(jwtToken);
        int userId = Integer.parseInt(claims.getSubject());     
        User user = UserHibernateDAO.getInstance().getUser(userId);
        return user;
    }
}