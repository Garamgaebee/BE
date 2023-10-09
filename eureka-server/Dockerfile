FROM openjdk:17
ARG JAR_FILE=eureka-server/build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9000
ENTRYPOINT ["sh", "-c", "java -jar -Dspring.profiles.active=dev /app.jar"]
