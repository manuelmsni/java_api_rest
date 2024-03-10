/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.middleware;

import javax.ws.rs.container.ResourceInfo;
import com.mycompany.api.annotation.NoSessionRequired;
import com.mycompany.api.dao.user.HibernateUserDAO;
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
 *
 * @author manuelmsni
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class SessionMiddleware implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

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

    private User getSessionUser(String jwtToken) {
        
        JWTManager.verifyToken(jwtToken);
        
        Claims claims = JWTManager.decodeToken(jwtToken);
        
        int userId = Integer.parseInt(claims.getSubject());
                
        User user = HibernateUserDAO.getInstance().getUser(userId);
        
        return user;
    }
}