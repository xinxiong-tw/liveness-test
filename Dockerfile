FROM openjdk:17

CMD mkdir app
ADD build/libs/liveness-test-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
