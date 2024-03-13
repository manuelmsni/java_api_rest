/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.util;

/**
 * Constantes de la aplicación
 * 
 * @author manuelmsni
 */
public class Constants {
    
    public static final String CONFIG_FILE = "app.conf"; // Ruta del fichero de configuración
    
    public static final long SESSION_VALIDITY = 24 * 60 * 60 * 1000; // Vida útil del token (24h en milisegundos)
    
    public static final int MONGODB_PORT = 27017; // Puerto de mongodb
}
