FROM openjdk:17
ARG JAR_FILE=image-service/image-container/build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9001
ENTRYPOINT ["sh", "-c", "java -jar -Dspring.profiles.active=dev /app.jar"]