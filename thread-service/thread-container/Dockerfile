FROM openjdk:17
ARG JAR_FILE=thread-service/thread-container/build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8084
ENTRYPOINT ["sh", "-c", "java -jar -Dspring.profiles.active=dev /app.jar"]
