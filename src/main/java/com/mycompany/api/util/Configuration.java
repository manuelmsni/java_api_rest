/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.util;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase para la gestión de la configuración de la aplicación. Permite cargar,
 * acceder y modificar propiedades de configuración almacenadas en un archivo.
 * Utiliza la clase {@link Properties} de Java para leer y escribir en el archivo
 * de configuración de forma sencilla y eficiente.
 * 
 * @author manuelmsni
 */
public class Configuration {

    private static Configuration instance;
    private Properties properties;

    private Configuration() {
        loadProperties();
    }

    public static Configuration getInstance(String configFile) {
        if (instance == null) instance = new Configuration();
        return instance;
    }

    /**
     * Carga las propiedades de configuración desde el archivo de configuración.
     * Se espera que el archivo se encuentre en la ruta especificada por la constante
     * {@link Constants#CONFIG_FILE}.
     */
    private void loadProperties() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(Constants.CONFIG_FILE)) {
            properties.load(fis);
        } catch (IOException ioe) {
            System.err.println("Error al cargar el archivo de configuración: " + ioe.getMessage());
        }
    }

    /**
     * Obtiene el valor de una propiedad de configuración especificada por su clave.
     * 
     * @param key La clave de la propiedad a obtener.
     * @return El valor de la propiedad, o null si la clave no existe.
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Establece o actualiza el valor de una propiedad de configuración. Si la clave
     * ya existe, su valor será actualizado; si no, la propiedad se creará con el valor
     * especificado. Después de modificar las propiedades, el archivo de configuración
     * se actualiza automáticamente para reflejar los cambios.
     * 
     * @param key La clave de la propiedad a establecer o actualizar.
     * @param value El nuevo valor para la propiedad.
     */
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        saveProperties();
    }

    /**
     * Guarda las propiedades de configuración actuales en el archivo de configuración.
     * Se utiliza después de modificar cualquier propiedad para asegurar que los cambios
     * se persistan en el archivo.
     */
    private void saveProperties() {
        try (FileOutputStream fos = new FileOutputStream(Constants.CONFIG_FILE)) {
            properties.store(fos, "Archivo de configuración actualizado");
        } catch (IOException ioe) {
            System.err.println("Error al guardar el archivo de configuración: " + ioe.getMessage());
        }
    }
}