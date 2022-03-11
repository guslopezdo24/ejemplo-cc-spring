FROM amazoncorretto:11-alpine3.15
WORKDIR /app
COPY ./build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "app.jar" ]