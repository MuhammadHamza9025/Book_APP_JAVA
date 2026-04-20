# Stage 1: Build the WAR using Maven
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -B -DskipTests clean install

# Stage 2: Deploy the WAR to Tomcat
FROM tomcat:10-jdk21-temurin

# Remove default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the built WAR from the builder stage
COPY --from=builder /app/target/BookLibrary.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
