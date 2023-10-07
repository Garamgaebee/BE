FROM openjdk:17
ARG JAR_FILE=member-service/member-container/build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8082
ENTRYPOINT ["sh", "-c", "java -jar -Dspring.profiles.active=dev /app.jar"]
