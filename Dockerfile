FROM openjdk:17
WORKDIR /app
COPY target/Parser-0.0.1-SNAPSHOT.jar /app/parser.jar
ENTRYPOINT ["java", "-jar", "parser.jar"]
