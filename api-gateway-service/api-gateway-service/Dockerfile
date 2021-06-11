FROM adoptopenjdk/openjdk15:alpine
MAINTAINER rfetalvero
# COPY target/api-gateway-service-0.0.1-SNAPSHOT.jar api-gateway-service.jar
# ENTRYPOINT ["java","-jar","/api-gateway-service.jar"]

# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java","${JAVA_OPTS}","-jar","/app.jar"]
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]