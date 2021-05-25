FROM adoptopenjdk/openjdk11
MAINTAINER nyorja
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} cloud-config-server.jar
# ENTRYPOINT ["java","-jar","/cloud-config-server.jar"]
# ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /cloud-config-server.jar"]
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /cloud-config-server.jar" ]