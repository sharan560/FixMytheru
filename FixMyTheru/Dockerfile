# Stage 1: Build the Spring Boot app
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/FixMyTheru-0.0.1-SNAPSHOT.jar FixMyTheru.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "FixMyTheru.jar"]
