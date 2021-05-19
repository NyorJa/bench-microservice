FROM adoptopenjdk/openjdk11
MAINTAINER nyorja
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} cloud-config-server.jar
ENTRYPOINT ["java","-jar","/cloud-config-server.jar"]