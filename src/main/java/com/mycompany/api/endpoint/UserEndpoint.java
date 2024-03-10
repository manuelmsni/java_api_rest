/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.api.endpoint;

import com.mycompany.api.annotation.NoSessionRequired;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author manuelmsni
 */
@Path("user")
public class UserEndpoint {

    @Context
    private UriInfo context;

    /**
     * Retrieves representation of an instance of com.mycompany.api.UserResource
     * @return an instance of Response
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @NoSessionRequired
    public Response getJson() {

        String userJson = "{\"name\":\"user\"}";

        return Response.ok(userJson).build();
    }

    /**
     * PUT method for updating or creating an instance of UserResource
     * @param content representation for the resource
     * @return an instance of Response
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putJson(String content) {
        
        // TODO : Insertar usuario
        
        return Response.ok("{\"message\":\"User updated successfully\", \"content\":" + content + "}").build();
    }
}