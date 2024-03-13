/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación que indica que un método o clase no requiere verificación de sesión para ser accedido.
 * Se puede aplicar a nivel de clase o método en servicios REST para especificar puntos de acceso
 * que están disponibles sin necesidad de autenticación.
 * 
 * @author manuelmsni
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface NoSessionRequired {
}
