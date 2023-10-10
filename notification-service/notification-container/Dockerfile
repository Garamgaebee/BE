FROM openjdk:17
ARG JAR_FILE=notification-service/notification-container/build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8085
ENTRYPOINT ["sh", "-c", "java -jar -Dspring.profiles.active=dev /app.jar"]