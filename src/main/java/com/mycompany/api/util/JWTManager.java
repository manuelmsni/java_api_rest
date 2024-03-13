/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.util;


import com.mycompany.api.dao.user.UserDAO;
import com.mycompany.api.dao.user.UserHibernateDAO;
import com.mycompany.api.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.ws.rs.HeaderParam;

/**
 * Clase encargada de manejar la generación, verificación y decodificación de tokens JWT.
 * Utiliza la biblioteca jjwt para crear tokens seguros que permiten autenticar y autorizar
 * a los usuarios en la aplicación. Los tokens incluyen información sobre el usuario y su periodo
 * de validez.
 * 
 * @author manuelmsni
 */
public class JWTManager {
    
    /**
     * Genera un token JWT para un usuario específico. El token incluye el ID del usuario
     * como sujeto y tiene un periodo de validez configurado.
     * 
     * @param user El usuario para el cual generar el token.
     * @return Un string que representa el token JWT generado.
     */
    public static String generateToken(User user) {
        Date expiration = new Date(System.currentTimeMillis() + Constants.SESSION_VALIDITY);
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId())) // Encode the user ID as the "subject" of the token
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256,getSecret())
                .compact();
    }

    /**
     * Verifica si un token JWT es válido, es decir, si su firma es correcta y aún no ha expirado.
     * 
     * @param jwtToken El token JWT a verificar.
     * @return true si el token es válido, false en caso contrario.
     */
    public static boolean verifyToken(String jwtToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSecret()).build().parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Decodifica un token JWT y extrae las claims (reclamaciones) contenidas en él.
     * 
     * @param jwtToken El token JWT a decodificar.
     * @return Un objeto Claims que contiene la información extraída del token.
     */
    public static Claims decodeToken(String jwtToken) {
        return Jwts.parserBuilder().setSigningKey(getSecret()).build().parseClaimsJws(jwtToken).getBody();
    }
    
    /**
     * Obtiene el usuario asociado a un token JWT. Decodifica el token, extrae el ID del usuario
     * y utiliza este ID para recuperar el usuario de la base de datos.
     * 
     * @param authToken El token JWT incluido en el encabezado de autorización de una solicitud HTTP.
     * @return El usuario asociado al token, o null si el token es inválido o el usuario no existe.
     */
    private static String getSecret(){
        // TODO : Implement a secure method to get the secret
        return Hasher.sha256("defaultsecret");
    }
    
    /**
     * Obtiene la clave secreta utilizada para firmar los tokens JWT. Esta implementación es solo
     * un ejemplo y debe ser reemplazada por un método más seguro para generar y almacenar la clave secreta.
     * 
     * @return Una clave secreta para firmar los tokens JWT.
     */
    public static User getUserFromToken(@HeaderParam("Authorization") String authToken) {
        try {
            String jwtToken = authToken.substring(7);
            Claims claims = decodeToken(jwtToken);
            int userId = Integer.parseInt(claims.getSubject());
            UserDAO userDAO = UserHibernateDAO.getInstance();
            return userDAO.getUser(userId);
        } catch (Exception e) {
            // TODO: Manejar la excepción
            e.printStackTrace();
            return null;
        }
    }
}