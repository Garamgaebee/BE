FROM openjdk:17
ARG JAR_FILE=/api-gateway/build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar -Dspring.profiles.active=dev /app.jar"]
