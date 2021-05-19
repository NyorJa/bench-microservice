FROM adoptopenjdk/openjdk11
MAINTAINER nyorja
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} department-service.jar
ENTRYPOINT ["java","-jar","/department-service.jar"]