FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

LABEL authors="BHUVAN J N"

ENTRYPOINT ["java", "-jar", "app.jar"]