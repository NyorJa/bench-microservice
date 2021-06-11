FROM adoptopenjdk/openjdk15:alpine
MAINTAINER rfetalvero
COPY target/configuration-server-0.0.1-SNAPSHOT.jar configuration-server.jar
ENTRYPOINT ["java","-jar","/configuration-server.jar"]