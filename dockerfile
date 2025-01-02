# Use Maven to build the application
FROM maven:3.8.7-openjdk-19 AS builder
WORKDIR /app
COPY . . 
RUN mvn clean package -DskipTests

# Use a lightweight JDK image to run the application
FROM eclipse-temurin:19-jre AS runtime
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
