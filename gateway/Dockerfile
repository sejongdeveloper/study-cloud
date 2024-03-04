FROM openjdk:17-ea-slim
VOLUME /tmp
COPY build/libs/gateway-1.0.jar gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]