<<<<<<< HEAD
FROM tomcat:9.0-jdk21

COPY target/*.war /usr/local/tomcat/webapps/app.war

EXPOSE 8080

=======
# Use official Tomcat image with Java 21
FROM tomcat:10-jdk21

# Remove default webapps (optional, keeps container clean)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR file into Tomcat's webapps directory
COPY target/*.war /usr/local/tomcat/webapps/app.war

# Expose default Tomcat port
EXPOSE 8080

# Start Tomcat
>>>>>>> 8bd664c (nn)
CMD ["catalina.sh", "run"]
