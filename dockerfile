# Step 1: Use a base image with Java installed
FROM openjdk:11-jre-slim

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy the jar file into the container
COPY target/angularspringbackendreview-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port on which your Spring Boot app will run
EXPOSE 8080

# Step 5: Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
