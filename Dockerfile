FROM openjdk:17-jdk-slim

EXPOSE 8081

ARG JAR_FILE=target/doctor-service.jar
ADD ${JAR_FILE} doctor-service.jar

ENTRYPOINT exec java -jar /doctor-service.jar