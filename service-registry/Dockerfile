FROM adoptopenjdk/openjdk11
MAINTAINER nyorja
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} service-registry.jar
ENTRYPOINT ["java","-jar","/service-registry.jar"]