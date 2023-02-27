FROM openjdk:17-alpine
ADD /target/event-api-0.0.1-SNAPSHOT.jar event-api.jar
ENTRYPOINT ["java", "-jar", "event-api.jar"]