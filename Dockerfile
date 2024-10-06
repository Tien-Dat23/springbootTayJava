FROM openjdk:21

ARG JAR_FILE=target/tiendat-sample-code-0.0.1-SNAPSHOT.jar

ADD ${FILE_JAR} api-service.jar

ENTRYPOINT ["java", "-jar", "api-service.jar"]

EXPOSE 8080