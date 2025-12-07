FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app

# Copia o JAR compilado
COPY Lynkar/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
