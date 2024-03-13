/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * Configuración de la aplicación JAX-RS. Define la ruta base para los servicios REST
 * y registra los recursos y proveedores utilizados en la aplicación.
 * Extiende la clase {@link javax.ws.rs.core.Application} para configurar los componentes
 * de la aplicación de manera programática.
 * 
 * @author manuelmsni
 */
@javax.ws.rs.ApplicationPath("/api") // Base entry point
public class ApplicationSetup extends Application {

    /**
     * Sobrescribe el método getClasses para registrar los recursos y proveedores
     * REST de la aplicación. Este método es llamado por el framework JAX-RS para
     * descubrir todas las clases que conforman la aplicación REST, incluyendo
     * endpoints y cualquier otro componente necesario (como filtros y listeners).
     * 
     * @return Un conjunto de clases que representan los recursos y proveedores
     *         registrados en la aplicación.
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Añade las clases de recursos REST y proveedores a un conjunto que será registrado
     * en la aplicación. Este método es una conveniencia para mantener el método getClasses
     * limpio y organizado, especificando explícitamente cada clase que debe ser incluida
     * en la configuración de la aplicación.
     * 
     * @param resources El conjunto de clases a ser registrado. Se espera que este método
     *                  añada las clases relevantes a este conjunto.
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.mycompany.api.endpoint.LoginEndpoint.class);
        resources.add(com.mycompany.api.endpoint.PostEndpoint.class);
        resources.add(com.mycompany.api.endpoint.RegisterEndpoint.class);
        resources.add(com.mycompany.api.endpoint.SessionEndpoint.class);
        resources.add(com.mycompany.api.endpoint.UserEndpoint.class);
        resources.add(com.mycompany.api.endpoint.UsersEndpoint.class);
        resources.add(com.mycompany.api.middleware.SessionMiddleware.class);
    }
    
}
