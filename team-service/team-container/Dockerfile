FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8083
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]
