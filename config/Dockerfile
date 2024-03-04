FROM openjdk:17-ea-slim
VOLUME /tmp
COPY build/libs/config-1.0.jar config.jar
ENTRYPOINT ["java", "-jar", "config.jar"]