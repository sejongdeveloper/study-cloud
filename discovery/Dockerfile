FROM openjdk:17-ea-slim
VOLUME /tmp
COPY build/libs/discovery-1.0.jar discovery.jar
ENTRYPOINT ["java", "-jar", "discovery.jar"]