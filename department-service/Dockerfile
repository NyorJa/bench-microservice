FROM adoptopenjdk/openjdk11
MAINTAINER nyorja
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} department-service.jar
# ENTRYPOINT ["java","-jar","/department-service.jar"]
# ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /department-service.jar"]

ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /department-service.jar" ]