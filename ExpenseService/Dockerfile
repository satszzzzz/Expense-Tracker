FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY ./target/ExpenseService-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8095

CMD ["java", "-jar", "app.jar"]

