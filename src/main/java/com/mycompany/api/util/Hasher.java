/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 *
 * @author manuelmsni
 */
public class Hasher {
    
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
