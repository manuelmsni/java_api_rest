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

    private void loadProperties() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(Constants.CONFIG_FILE)) {
            properties.load(fis);
        } catch (IOException ioe) {
            System.err.println("Error al cargar el archivo de configuración: " + ioe.getMessage());
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        saveProperties();
    }

    private void saveProperties() {
        try (FileOutputStream fos = new FileOutputStream(Constants.CONFIG_FILE)) {
            properties.store(fos, "Archivo de configuración actualizado");
        } catch (IOException ioe) {
            System.err.println("Error al guardar el archivo de configuración: " + ioe.getMessage());
        }
    }
}