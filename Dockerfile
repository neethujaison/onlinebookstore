FROM openjdk:8-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bookstore.jar
ENTRYPOINT ["java", "-jar", "/bookstore.jar"]