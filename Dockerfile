FROM tomcat:9.0-jdk21

COPY target/*.war /usr/local/tomcat/webapps/app.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
