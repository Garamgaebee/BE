FROM openjdk:17
ARG JAR_FILE=team-service/team-container/build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8083
ENTRYPOINT ["sh", "-c", "java -jar -Dspring.profiles.active=dev /app.jar"]
