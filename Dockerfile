# Usa la imagen base oficial de Tomcat
FROM tomcat:9-jdk11-openjdk

# Elimina las aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia tu archivo WAR construido con Maven al directorio de aplicaciones de Tomcat
COPY target/api-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/api-1.0-SNAPSHOT.war

# Expone el puerto 8080 para acceder a tu aplicación
EXPOSE 8080

# Ejecuta Tomcat
CMD ["catalina.sh", "run"]