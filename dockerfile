# Step 1: Use Maven image to build the application
FROM maven:3.8.7-eclipse-temurin-19 AS builder

# Debug Step 1: Print the base image details and check Maven version
RUN echo "Using Maven Base Image" && java -version && mvn --version

WORKDIR /app

# Debug Step 2: List files to verify that the context is copied correctly
COPY . . 
RUN echo "Listing contents of /app after COPY:" && ls -la /app

# Debug Step 3: Run Maven clean package with verbose output
RUN mvn clean package -DskipTests -X

# Step 2: Use a lightweight JDK image to run the application
FROM eclipse-temurin:19-jre AS runtime

# Debug Step 4: Print JDK version in the runtime image
RUN echo "Using Runtime Base Image" && java -version

WORKDIR /app

# Debug Step 5: Verify the JAR file is copied from the builder stage
COPY --from=builder /app/target/*.jar app.jar
RUN echo "Listing contents of /app in the runtime stage:" && ls -la /app

# Application runtime setup
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
