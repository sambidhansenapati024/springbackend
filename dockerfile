FROM eclipse-temurin:19-jdk AS builder
WORKDIR /app

RUN ls -la / && ls -la /app
COPY . .
RUN ./mvn clean package -DskipTests

FROM eclipse-temurin:19-jre AS runtime
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
