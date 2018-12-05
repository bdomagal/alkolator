FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
COPY build/libs/alkolator-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]