FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY ./app/build/libs/app.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
