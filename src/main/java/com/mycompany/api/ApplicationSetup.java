/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author manuelmsni
 */
@javax.ws.rs.ApplicationPath("api") // Base entry point
public class ApplicationSetup extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.mycompany.api.endpoint.LoginEndpoint.class);
        resources.add(com.mycompany.api.endpoint.UserEndpoint.class);
        resources.add(com.mycompany.api.middleware.SessionMiddleware.class);
    }
    
}
