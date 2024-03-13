/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * Clase utilitaria para realizar operaciones de hashing, específicamente
 * proporciona una función para generar un hash SHA-256 de una cadena de texto.
 * Esta clase se puede utilizar para crear hashes seguros de contraseñas u otros
 * datos sensibles antes de almacenarlos o compararlos.
 * 
 * @author manuelmsni
 */
public class Hasher {
    
    /**
     * Genera un hash SHA-256 de una cadena de texto dada. Esta función es útil
     * para crear representaciones seguras de contraseñas u otros datos sensibles.
     * 
     * @param message La cadena de texto a ser hasheada.
     * @return El hash SHA-256 de la cadena de texto en formato hexadecimal.
     */
    public static String sha256(String message) {
        String sha256 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-256");
            crypt.reset();
            crypt.update(message.getBytes("UTF-8"));
            sha256 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha256;
    }

    /**
     * Convierte un array de bytes en una cadena hexadecimal. Este método es
     * utilizado internamente por la función sha256 para formatear el resultado del
     * hash en una cadena legible y comparable.
     * 
     * @param hash Array de bytes a convertir en formato hexadecimal.
     * @return La representación hexadecimal del array de bytes.
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    
}
