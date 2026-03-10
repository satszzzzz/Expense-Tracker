FROM openjdk:21
WORKDIR /app

COPY ./app/build/libs/app-1..jar /app/app-1.0.jar

EXPOSE 8080

CMD ["java", "-jar", "app-1.0.jar"]
